package cz.gopas.review.persistence;

import cz.gopas.review.bean.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{'stars':  { '$gte': ?0 }}")
    List<Review> getBestReviews(int minStars);

}
