package school.faang.user_service.dto.skill;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkillCandidateDto {

    SkillDto skillDto;

    long offersAmount;
}
