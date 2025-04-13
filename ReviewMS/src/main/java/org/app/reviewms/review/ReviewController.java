package org.app.reviewms.review;

import lombok.RequiredArgsConstructor;
import org.app.reviewms.review.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;
    private final ReviewMessageProducer reviewMessageProducer;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(service.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {

        boolean b = service.addReview(companyId, review);
        if (b) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review created",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-reviews")
    public ResponseEntity<String> addReviews( @RequestBody List<Review> reviews) {

        service.addReviews(reviews);
        return new ResponseEntity<>("reviews created !", HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(service.getReview(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
             @PathVariable Long reviewId
            ,@RequestBody Review review
    ) {

        boolean isReviewUpdated = service.updateReview(reviewId, review);

        if (isReviewUpdated) {
            return new ResponseEntity<>("Review updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean isReviewDeleted = service.deleteReview(reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId) {
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }





}
