package cz.gopas.review.persistence;

import cz.gopas.review.bean.Review;

import java.util.List;
import java.util.Optional;

public interface Storage {

    List<Review> getAll();
    Optional<Review> getReviewById(String id);
    Review createBook(Review newReview);
    Optional<Review> deleteBookById(String id);

}
