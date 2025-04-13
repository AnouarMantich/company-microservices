package org.app.reviewms.review.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.reviewms.review.Review;
import org.app.reviewms.review.ReviewRepository;
import org.app.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;




    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);


    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId !=null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public void addReviews( List<Review> reviews) {
        for(Review review : reviews){
            if( review != null){
                review.setCompanyId(review.getCompanyId());
                reviewRepository.save(review);
            }
        }
    }

    @Override
    public Review getReview( Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview( Long reviewId, Review review) {

        Review existingReview = reviewRepository.findById(reviewId).orElse(null);
        if(existingReview != null){

            existingReview.setTitle(review.getTitle());
            existingReview.setDescription(review.getDescription());
            existingReview.setRating(review.getRating());
            existingReview.setCompanyId(review.getCompanyId());
            reviewRepository.save(existingReview);
            return true;

        }

        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
