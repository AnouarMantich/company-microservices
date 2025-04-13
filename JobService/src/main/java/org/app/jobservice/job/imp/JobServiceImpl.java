package org.app.jobservice.job.imp;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.app.jobservice.Dtos.JobDTO;
import org.app.jobservice.job.Job;
import org.app.jobservice.job.JobRepository;
import org.app.jobservice.job.JobService;
import org.app.jobservice.job.mapper.JobMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private JobMapper jobMapper;


    @Override
    @RateLimiter(  name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAllJobs() {
        List<Job> all = jobRepository.findAll();

        return all.stream().map(jobMapper::toJobWithCompanyDTO).collect(Collectors.toList());
    }
    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return jobMapper.toJobWithCompanyDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
       try{
           jobRepository.deleteById(id);
           return true;
       }catch(Exception e){
           return false;
       }
    }

    @Override
    public boolean updateJob(Long id,Job updatedJob) {
        Job job= jobRepository.findById(id).orElse(null);

        if (job!=null){
            updatedJob.setTitle(job.getTitle());
            updatedJob.setDescription(job.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public void createJobs(List<Job> jobs) {
        for (Job job : jobs) {
            createJob(job);
        }
    }
}
