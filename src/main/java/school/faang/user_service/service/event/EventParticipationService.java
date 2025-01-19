package school.faang.user_service.service.event;

import school.faang.user_service.entity.User;

import java.util.List;

public interface EventParticipationService {


    void registerParticipant(long eventId, long userId);

    void unregisterParticipant(long eventId, long userId);

    List<User> getParticipant(long eventId);

    int getParticipantsCount(long eventId);
}
