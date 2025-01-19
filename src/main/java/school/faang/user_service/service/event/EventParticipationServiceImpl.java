package school.faang.user_service.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventParticipationServiceImpl implements EventParticipationService {

    @Override
    public void registerParticipant(long eventId, long userId) {

    }

    @Override
    public void unregisterParticipant(long eventId, long userId) {

    }

    @Override
    public List<User> getParticipant(long eventId) {
        return List.of();
    }

    @Override
    public int getParticipantsCount(long eventId) {
        return 0;
    }
}
