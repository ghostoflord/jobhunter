package vn.ghost.jobhunter.service;

import vn.ghost.jobhunter.domain.Company;
import vn.ghost.jobhunter.domain.Job;
import vn.ghost.jobhunter.domain.User;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import vn.ghost.jobhunter.domain.response.ResJobDTO;
import vn.ghost.jobhunter.domain.response.ResUserDTO;
import vn.ghost.jobhunter.domain.response.ResultPaginationDTO;
import vn.ghost.jobhunter.repository.JobRepository;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // POST JOB
    public Job handleCreateJob(Job job) {
        return this.jobRepository.save(job);
    }

    // Get All job
    public ResultPaginationDTO fetchAllJob(Specification<Job> spec, Pageable pageable) {
        Page<Job> pageJob = this.jobRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageJob.getTotalPages());
        mt.setTotal(pageJob.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageJob.getContent());

        rs.setMeta(mt);
        return rs;
    }

    // Get Job By Id
    public Job fetchJobById(long id) {
        Optional<Job> jobOptional = this.jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }
        return null;
    }

    // Delete Job
    public void handleDeleteJob(long id) {
        this.jobRepository.deleteById(id);
    }

    // Put Job
    public Job handleUpdateJob(Job reqJob) {
        Job currentJob = this.fetchJobById(reqJob.getId());
        if (currentJob != null) {
            currentJob.setName(reqJob.getName());
            currentJob.setSalary(reqJob.getSalary());
            currentJob.setQuantity(reqJob.getQuantity());
            currentJob.setLevel(reqJob.getLevel());
            currentJob.setDescription(reqJob.getDescription());
            // check company
            // if (reqUser.getCompany() != null) {
            // Optional<Company> companyOptional =
            // this.companyService.findById(reqUser.getCompany().getId());
            // reqUser.setCompany(companyOptional.isPresent() ? companyOptional.get() :
            // null);
            // }

            // save and update
            currentJob = this.jobRepository.save(currentJob);
        }
        return currentJob;
    }

}
