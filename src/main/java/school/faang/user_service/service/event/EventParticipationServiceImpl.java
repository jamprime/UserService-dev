package school.faang.user_service.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.user.UserDto;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.mapper.UserMapper;
import school.faang.user_service.repository.event.EventParticipationRepository;
import school.faang.user_service.validator.event.EventParticipationValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventParticipationServiceImpl implements EventParticipationService {

    private final EventParticipationRepository eventParticipationRepository;
    private final EventParticipationValidator eventParticipationValidator;
    private final UserMapper userMapper;

    @Override
    public void registerParticipant(long eventId, long userId) {

        if (eventParticipationValidator.validateEventParticipation(eventId, userId)) {
            throw new DataValidationException("User already registered for the event");
        }

        eventParticipationRepository.register(eventId, userId);
    }

    @Override
    public void unregisterParticipant(long eventId, long userId) {

        if (!eventParticipationValidator.validateEventParticipation(eventId, userId)) {
            throw new DataValidationException("User not registered on the event");
        }

        eventParticipationRepository.unregister(eventId, userId);
    }

    @Override
    public List<UserDto> getParticipant(long eventId) {

        return eventParticipationRepository.findAllParticipantsByEventId(eventId)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public int getParticipantsCount(long eventId) {

        return eventParticipationRepository.countParticipants(eventId);
    }
}
