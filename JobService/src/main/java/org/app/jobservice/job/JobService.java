package org.app.jobservice.job;

import org.app.jobservice.Dtos.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> findAllJobs();
    void createJob(Job job);
    JobDTO getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id,Job job);
    void createJobs(List<Job> jobs);

}
