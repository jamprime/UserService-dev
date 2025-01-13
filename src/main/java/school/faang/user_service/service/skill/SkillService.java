package school.faang.user_service.service.skill;

import org.springframework.stereotype.Service;
import school.faang.user_service.dto.skill.SkillCandidateDto;
import school.faang.user_service.dto.skill.SkillDto;

import java.util.List;

public interface SkillService {

    SkillDto create(SkillDto skill);

    List<SkillDto> getUserSkills(Long id);

    List<SkillCandidateDto> getOfferedSkills(long userId);

    SkillDto acquireSkillFromOffers(long skillId, long userId);
}
