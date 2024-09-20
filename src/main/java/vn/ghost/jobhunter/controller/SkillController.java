package vn.ghost.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.ghost.jobhunter.domain.Job;
import vn.ghost.jobhunter.domain.Skill;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.domain.response.ResCreateUserDTO;
import vn.ghost.jobhunter.service.SkillService;
import vn.ghost.jobhunter.util.annotation.ApiMessage;
import vn.ghost.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/skills")
    @ApiMessage("Create a new skill")
    public ResponseEntity<Skill> createUser(@Valid @RequestBody Skill postManSkill) {
        Skill ghostSkill = this.skillService.handleCreateSkill(postManSkill);
        return ResponseEntity.status(HttpStatus.CREATED).body(ghostSkill);
    }

}
