package school.faang.user_service.controller.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import school.faang.user_service.dto.skill.SkillCandidateDto;
import school.faang.user_service.dto.skill.SkillDto;
import school.faang.user_service.service.skill.SkillService;
import school.faang.user_service.validator.skill.SkillValidator;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SkillController {

    private SkillService skillService;
    private SkillValidator skillValidator;

    public SkillDto create(SkillDto skill) {

        skillValidator.validateSkill(skill);

        return skillService.create(skill);
    }

    public List<SkillDto> getUserSkills(long id) {

        return skillService.getUserSkills(id);
    }

    List<SkillCandidateDto> getOfferedSkills(long userId) {


    }

}
