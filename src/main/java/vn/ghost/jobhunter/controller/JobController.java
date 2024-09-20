package vn.ghost.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.ghost.jobhunter.domain.Job;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.domain.response.ResUpdateUserDTO;
import vn.ghost.jobhunter.domain.response.ResultPaginationDTO;
import vn.ghost.jobhunter.service.JobService;
import vn.ghost.jobhunter.util.annotation.ApiMessage;
import vn.ghost.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    @ApiMessage("fetch all job")
    public ResponseEntity<ResultPaginationDTO> getAllUser(
            @Filter Specification<Job> spec,
            Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.jobService.fetchAllJob(spec, pageable));
    }

    @PostMapping("/jobs")
    @ApiMessage("Create a new job")
    public ResponseEntity<Job> createUser(@Valid @RequestBody Job postManJob) {
        Job ghostJob = this.jobService.handleCreateJob(postManJob);
        return ResponseEntity.status(HttpStatus.CREATED).body(ghostJob);
    }

    @DeleteMapping("/jobs/{id}")
    @ApiMessage("Delete a job")
    public ResponseEntity<Void> deleteJob(@PathVariable("id") long id) {
        this.jobService.handleDeleteJob(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/jobs")
    public ResponseEntity<Job> updateJob(@Valid @RequestBody Job job) {
        Job ghostJob = this.jobService.handleUpdateJob(job);
        return ResponseEntity.ok(ghostJob);
    }

}
