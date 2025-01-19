package school.faang.user_service.service.event;

import school.faang.user_service.dto.event.EventDto;
import school.faang.user_service.dto.event.EventFilterDto;

import java.util.List;

public interface EventService {

    EventDto create(EventDto event);

    EventDto getEvent(long eventId);

    List<EventDto> getEventsByFilter(EventFilterDto filter);

    EventDto deleteEvent(long eventId);

    EventDto updateEvent(EventDto event);

    List<EventDto> getOwnedEvents(long userId);

    List<EventDto> getParticipatedEvents(long userId);
}
