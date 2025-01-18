package school.faang.user_service.dto.skill;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SkillDto {

    Long id;
    String title;
    List<Long> userIds;
}