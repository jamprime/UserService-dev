package school.faang.user_service.validator.goal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.faang.user_service.dto.goal.GoalInvitationDto;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.repository.UserRepository;
import school.faang.user_service.repository.goal.GoalInvitationRepository;

@Component
@RequiredArgsConstructor
public class GoalInvitationValidator {

    private final GoalInvitationRepository goalInvitationRepository;
    private final UserRepository userRepository;

    public void validateGoalInvitation(GoalInvitationDto invitation) {

        if (invitation.getInvitedUserId().equals(invitation.getInviterId())) {

            throw new DataValidationException("Inviter and Invited can't be one person");
        } else {

            if (userRepository.findById(invitation.getInvitedUserId()).isEmpty()) {

                throw new DataValidationException("Invited person doesn't exist in our system");
            }
            if (userRepository.findById(invitation.getInviterId()).isEmpty()) {

                throw new DataValidationException("Inviter doesn't exist in our system");
            }
        }
    }
}
