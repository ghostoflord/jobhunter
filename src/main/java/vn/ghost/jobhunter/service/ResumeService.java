package vn.ghost.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.ghost.jobhunter.domain.Resume;
import vn.ghost.jobhunter.repository.ResumeRepository;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    // create Resume
    public Resume handleCreateUser(Resume resume) {
        return this.resumeRepository.save(resume);
    }
}
