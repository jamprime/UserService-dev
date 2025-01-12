package school.faang.user_service.service.skill;

import org.springframework.stereotype.Service;
import school.faang.user_service.dto.skill.SkillDto;

@Service
public interface SkillService {

    SkillDto create(SkillDto skill);
}
