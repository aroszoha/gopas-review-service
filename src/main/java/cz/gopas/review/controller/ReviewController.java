package cz.gopas.review.controller;

import cz.gopas.review.bean.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReviewController {

    @GetMapping("/reviews")
    public List<Review> getReviews();

    @GetMapping("/review")
    public ResponseEntity<Review> getReview(@RequestParam("id") String id);

    @PostMapping("/review")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> createReview(@RequestBody Review newReview);

    @DeleteMapping("/review")
    public ResponseEntity<Review> deleteReview(@RequestParam("id") String id);

}
