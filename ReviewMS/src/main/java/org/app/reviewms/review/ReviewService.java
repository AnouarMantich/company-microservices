package org.app.reviewms.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId,Review review);
    void addReviews(List<Review> reviews);
    Review getReview(Long reviewId);
    boolean updateReview( Long reviewId, Review review);


    boolean deleteReview(Long reviewId);
}
