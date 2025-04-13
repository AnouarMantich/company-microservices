package org.app.jobservice.job;


import lombok.AllArgsConstructor;
import org.app.jobservice.Dtos.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {

    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobDTO>> getJobById(){
        return ResponseEntity.ok(jobService.findAllJobs());
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        return ResponseEntity.ok(jobService.getJobById(id));
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created", HttpStatus.CREATED);
    }

    @PostMapping("/create-jobs")
    public ResponseEntity<String> createJobs(@RequestBody List<Job> jobs){
        jobService.createJobs(jobs);
        return new ResponseEntity<>("Jobs created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id){
        boolean deleted=jobService.deleteJobById(id);
        if (deleted){
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not deleted", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id,@RequestBody Job job){
        boolean updated =jobService.updateJob(id,job);
        if (updated){
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not updated", HttpStatus.NOT_FOUND);
    }

}
