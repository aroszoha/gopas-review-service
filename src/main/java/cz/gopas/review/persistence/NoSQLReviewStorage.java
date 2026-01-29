package cz.gopas.review.persistence;

import cz.gopas.review.bean.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Primary
@Service
public class NoSQLReviewStorage implements Storage {

    private final ReviewRepository reviewRepo;

    @Override
    public List<Review> getAll() {
        return reviewRepo.findAll();
    }

    @Override
    public Optional<Review> getReviewById(String id) {
        return reviewRepo.findById(id);
    }

    @Override
    public Review createBook(Review newReview) {
        reviewRepo.save(newReview);
        return newReview;
    }

    @Override
    public Optional<Review> deleteBookById(String id) {
        Optional<Review> foundReview = getReviewById(id);
        if (foundReview.isEmpty()) {
            return Optional.empty();
        } else {
            reviewRepo.delete(foundReview.get());
            return foundReview;
        }
    }

}
