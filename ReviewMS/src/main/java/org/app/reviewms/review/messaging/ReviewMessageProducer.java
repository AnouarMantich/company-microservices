package org.app.reviewms.review.messaging;

import lombok.AllArgsConstructor;
import org.app.reviewms.review.Review;
import org.app.reviewms.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(final Review review) {

        ReviewMessage reviewMessage = ReviewMessage.builder()
                .id(review.getId())
                .title(review.getTitle())
                .description(review.getDescription())
                .rating(review.getRating())
                .companyId(review.getCompanyId())
                .build();

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);

    }
}
