package school.faang.user_service.service.goal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.goal.GoalInvitationDto;
import school.faang.user_service.dto.goal.GoalInvitationFilterIDto;
import school.faang.user_service.entity.goal.GoalInvitation;
import school.faang.user_service.filter.goal_filter.GoalInvitationFilter;
import school.faang.user_service.mapper.GoalInvitationMapper;
import school.faang.user_service.repository.goal.GoalInvitationRepository;
import school.faang.user_service.validator.goal.GoalInvitationValidator;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GoalInvitationServiceImpl implements GoalInvitationService{

    private final GoalInvitationRepository goalInvitationRepository;
    private final GoalInvitationValidator goalInvitationValidator;
    private final GoalInvitationMapper goalInvitationMapper;
    private final List<GoalInvitationFilter> goalInvitationFilters;

    @Override
    public GoalInvitationDto createInvitation(GoalInvitationDto invitation) {

        goalInvitationValidator.validateGoalInvitation(invitation);

        return goalInvitationMapper.toDto(goalInvitationRepository.save(goalInvitationMapper.toEntity(invitation)));
    }

    @Override
    public void acceptGoalInvitation(long id) {


    }

    @Override
    public void rejectGoalInvitation(long id) {

    }

    @Override
    public List<GoalInvitationDto> getInvitations(GoalInvitationFilterIDto filters) {

        Stream<GoalInvitation> list = goalInvitationRepository.findAll().stream();

        goalInvitationFilters.stream()
                .filter(filter -> filter.isApplicable(filters))
                .forEach(filter -> filter.apply(list, filters));

        return list.map(goalInvitationMapper::toDto).toList();
    }
}
