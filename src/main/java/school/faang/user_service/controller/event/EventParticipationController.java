package school.faang.user_service.controller.event;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.faang.user_service.dto.user.UserDto;
import school.faang.user_service.service.event.EventParticipationService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventParticipationController {

    private final EventParticipationService eventParticipationService;

    @PostMapping("/{eventId}/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registerParticipant(@PathVariable long eventId, @PathVariable long userId) {

        eventParticipationService.registerParticipant(eventId, userId);
    }

    @DeleteMapping("/{eventId}/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void unregisterParticipant(@PathVariable long eventId, @PathVariable long userId)  {

        eventParticipationService.unregisterParticipant(eventId, userId);
    }

    @GetMapping("/{eventId}")
    public List<UserDto> getParticipant(@PathVariable long eventId) {

        return eventParticipationService.getParticipant(eventId);
    }

    @GetMapping("count/{eventId}")
    public int getParticipantsCount(@PathVariable long eventId) {

        return eventParticipationService.getParticipantsCount(eventId);
    }
}
