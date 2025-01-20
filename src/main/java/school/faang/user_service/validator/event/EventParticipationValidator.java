package school.faang.user_service.validator.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.faang.user_service.entity.User;
import school.faang.user_service.repository.event.EventParticipationRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventParticipationValidator {

    private final EventParticipationRepository eventParticipationRepository;

    public boolean validateEventParticipation(long eventId, long userId) {

        List<User> list = eventParticipationRepository.findAllParticipantsByEventId(eventId);

        return list.stream().anyMatch(x -> x.getId() == userId);
    }
}
