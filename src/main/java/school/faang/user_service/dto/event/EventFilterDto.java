package school.faang.user_service.dto.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import school.faang.user_service.dto.skill.SkillDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EventFilterDto {

    private String titlePattern;

    private String descriptionPattern;

    private LocalDateTime startDatePattern;

    private LocalDateTime endDatePattern;

    private String locationPattern;

    private int maxAttendeesPattern;

    private Long ownerIdPattern;

    private List<SkillDto> relatedSkillsPattern;
}
