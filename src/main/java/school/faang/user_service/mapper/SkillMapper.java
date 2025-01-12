package school.faang.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import school.faang.user_service.dto.skill.SkillDto;
import school.faang.user_service.entity.Skill;

@Mapper
public interface SkillMapper {

    Skill toEntity(SkillDto skillDto);

    SkillDto toDto(Skill skill);

}
