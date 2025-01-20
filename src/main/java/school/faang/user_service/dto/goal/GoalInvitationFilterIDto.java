package school.faang.user_service.dto.goal;

import lombok.Data;
import lombok.NoArgsConstructor;
import school.faang.user_service.entity.RequestStatus;

@Data
@NoArgsConstructor
public class GoalInvitationFilterIDto {

    private String inviterNamePattern;

    private String invitedNamePattern;

    private Long inviterId;

    private Long invitedId;

    private RequestStatus status;
}
