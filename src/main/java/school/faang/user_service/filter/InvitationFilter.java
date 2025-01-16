package school.faang.user_service.filter;

import school.faang.user_service.dto.user.UserFilterDto;
import school.faang.user_service.entity.User;
import school.faang.user_service.entity.goal.GoalInvitation;

import java.util.List;
import java.util.stream.Stream;

public interface InvitationFilter {

    boolean isApplicable(UserFilterDto filters);

    List<User> apply(Stream<GoalInvitation> invitations, UserFilterDto filters);
}
