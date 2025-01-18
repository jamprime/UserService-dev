package school.faang.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import school.faang.user_service.dto.skill.SkillCandidateDto;
import school.faang.user_service.dto.skill.SkillDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.entity.User;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SkillMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userIds", target = "users", qualifiedByName = "map")
    Skill toEntity(SkillDto skillDto);

    @Mapping(source = "users", target = "userIds", qualifiedByName = "map")
    @Mapping(source = "id", target = "id")
    SkillDto toDto(Skill skill);

    @Mapping(source = "skill.id", target = "skillDto.id")
    @Mapping(source = "skill.title", target = "skillDto.title")
    SkillCandidateDto toCandidateDto(Skill skill);


    @Named("map")
    private List<Long> map(List<User> users) {

        return users.stream().map(User::getId).toList();
    }
}
