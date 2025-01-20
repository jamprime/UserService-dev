package school.faang.user_service.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import school.faang.user_service.dto.event.EventDto;
import school.faang.user_service.dto.event.EventFilterDto;
import school.faang.user_service.service.event.EventService;
import school.faang.user_service.service.event.EventServiceImpl;

import java.util.List;

@RestController("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(@RequestBody EventDto event) {

        return eventService.create(event);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto getEvent(@PathVariable long eventId) {

        return eventService.getEvent(eventId);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getEventsByFilter(@RequestBody EventFilterDto filterDto) {

        return eventService.getEventsByFilter(filterDto);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto deleteEvent(@PathVariable long eventId) {

        return eventService.deleteEvent(eventId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateEvent(@RequestBody EventDto event) {

        return eventService.updateEvent(event);
    }

    @GetMapping("by/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getOwnedEvents(@PathVariable long userId) {

        return eventService.getOwnedEvents(userId);
    }

    @GetMapping("participated/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getParticipatedEvents(@PathVariable long userId) {

        return eventService.getParticipatedEvents(userId);
    }
}
