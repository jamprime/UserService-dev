package school.faang.user_service.controller.goal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import school.faang.user_service.dto.goal.GoalInvitationDto;
import school.faang.user_service.dto.goal.GoalInvitationFilterIDto;
import school.faang.user_service.service.goal.GoalInvitationService;

import java.util.List;

@RequiredArgsConstructor
@Controller("/goal")
public class GoalInvitationController {

    private final GoalInvitationService goalInvitationService;


    @PostMapping
    public GoalInvitationDto createInvitation(GoalInvitationDto invitation) {

        return goalInvitationService.createInvitation(invitation);
    }

    @GetMapping("/all")
    public List<GoalInvitationDto> getInvitations(GoalInvitationFilterIDto filters) {

        return goalInvitationService.getInvitations(filters);
    }
}
