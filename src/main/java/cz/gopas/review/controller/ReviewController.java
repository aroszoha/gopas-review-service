package cz.gopas.review.controller;

import cz.gopas.review.bean.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReviewController {

    @GetMapping("/reviews")
    public List<Review> getReviews();

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReview(@PathVariable("id") String id);

    @PostMapping("/reviews")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> createReview(@RequestBody Review newReview);

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable("id") String id);

}
