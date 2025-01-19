package school.faang.user_service.dto.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import school.faang.user_service.dto.skill.SkillDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EventDto {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String location;

    private int maxAttendees;

    private Long ownerId;

    private List<SkillDto> relatedSkills;
}
