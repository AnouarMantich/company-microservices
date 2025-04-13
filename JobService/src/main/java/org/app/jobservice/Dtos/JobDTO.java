package org.app.jobservice.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.jobservice.job.external.Company;
import org.app.jobservice.job.external.Review;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class JobDTO {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;

}
