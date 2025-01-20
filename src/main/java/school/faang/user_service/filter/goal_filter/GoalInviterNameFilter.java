package school.faang.user_service.filter.goal_filter;

import school.faang.user_service.dto.goal.GoalInvitationFilterIDto;
import school.faang.user_service.entity.goal.GoalInvitation;

import java.util.stream.Stream;

public class GoalInviterNameFilter implements GoalInvitationFilter{

    @Override
    public boolean isApplicable(GoalInvitationFilterIDto filters) {
        return !filters.getInviterNamePattern().isEmpty();
    }

    @Override
    public void apply(Stream<GoalInvitation> stream, GoalInvitationFilterIDto filters) {
        stream.filter(goal -> goal.getInviter().getUsername().equals(filters.getInviterNamePattern()));
    }
}
