package cz.gopas.review.controller;

import cz.gopas.review.bean.Review;
import cz.gopas.review.persistence.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1")
public class ReviewControllerV1 implements ReviewController {

    @Autowired
    private Storage storage;

    @Autowired
    private RestTemplate bookServiceClient;

    @Value("${custom.book-service-base-url}")
    private String bookServiceBaseURL;

    @Override
    public List<Review> getReviews() {
        return storage.getAll();
    }

    @Override
    public ResponseEntity<Review> getReview(String id) {
        Optional<Review> result = storage.getReviewById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get());
        }
    }

    @Override
    public ResponseEntity<Review> createReview(Review newReview) {
        try {
            ResponseEntity<String> serviceResult = bookServiceClient.getForEntity(bookServiceBaseURL + newReview.getBookId(), String.class);
            log.info(serviceResult.getBody());
            Review result = storage.createBook(newReview);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("X-ReviewID", String.valueOf(result.getId()))
                    .body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Review> deleteReview(String id) {
        Optional<Review> result = storage.deleteBookById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok()
                    .header("X-ReviewID", String.valueOf(result.get().getId()))
                    .build();
        }
    }
}
