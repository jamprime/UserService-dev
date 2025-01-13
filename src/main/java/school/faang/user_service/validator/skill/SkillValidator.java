package school.faang.user_service.validator.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.faang.user_service.dto.skill.SkillDto;
import school.faang.user_service.exception.DataValidationException;


@Component
@RequiredArgsConstructor
public class SkillValidator {

    public void validateSkill(SkillDto skill) {

        if (skill.getTitle() == null || skill.getTitle().isEmpty()) {
            throw new DataValidationException("Can not create skill without title");
        }
    }
}
