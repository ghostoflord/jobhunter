package vn.ghost.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.ghost.jobhunter.domain.Skill;
import vn.ghost.jobhunter.repository.JobRepository;
import vn.ghost.jobhunter.repository.SkillRepository;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill handleCreateSkill(Skill skill) {
        return this.skillRepository.save(skill);
    }

}
