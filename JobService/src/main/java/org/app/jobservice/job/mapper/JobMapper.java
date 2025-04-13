package org.app.jobservice.job.mapper;

import lombok.AllArgsConstructor;
import org.app.jobservice.Dtos.JobDTO;
import org.app.jobservice.job.Job;
import org.app.jobservice.job.clients.CompanyClient;
import org.app.jobservice.job.clients.ReviewClient;
import org.app.jobservice.job.external.Company;
import org.app.jobservice.job.external.Review;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class JobMapper {
//    private final RestTemplate restTemplate;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

public JobDTO toJobWithCompanyDTO(Job job) {
    JobDTO jobDTO = new JobDTO();
    BeanUtils.copyProperties(job, jobDTO);
    Company company = companyClient.getCompany(job.getCompanyId());

    List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

    jobDTO.setCompany(company);
    jobDTO.setReviews(reviews);
    return jobDTO;
}
}
