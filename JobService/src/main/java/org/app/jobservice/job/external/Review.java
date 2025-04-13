package org.app.jobservice.job.external;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private Long id;
    private String title;
    private String description;
    private double rating;
}
