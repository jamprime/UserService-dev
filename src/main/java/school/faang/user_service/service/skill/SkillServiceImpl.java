package school.faang.user_service.service.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.skill.SkillDto;
import school.faang.user_service.mapper.SkillMapper;
import school.faang.user_service.repository.SkillRepository;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService{

    private SkillRepository skillRepository;
    private SkillMapper skillMapper;

    public SkillDto create(SkillDto skill) {

        if (skillRepository.existsByTitle(skill.title)) {
            throw new RuntimeException("There is exist skill with same title");
        }

        return skillMapper.toDto(skillRepository.save(skillMapper.toEntity(skill)));
    }

}
