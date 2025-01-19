package school.faang.user_service.validator.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.faang.user_service.dto.event.EventDto;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.repository.event.EventRepository;

@Component
@RequiredArgsConstructor
public class EventValidator {

    private final EventRepository eventRepository;

    public void validateEvent(EventDto eventDto) {

        if (eventDto.getTitle().isEmpty() || eventDto.getStartDate() == null || eventDto.getOwnerId() == null) {

            throw new DataValidationException("Event title, date of start and owner Id can't be empty");
        }
    }
}
