package vn.ghost.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.ghost.jobhunter.domain.Resume;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.service.ResumeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")

public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/resumes")
    public String getResume() {
        return "form resume controller";
    }

    @PostMapping("/resumes")
    public ResponseEntity<Resume> createResume(@Valid @RequestBody Resume postManResume) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.resumeService.handleCreateUser(postManResume));
    } 


}