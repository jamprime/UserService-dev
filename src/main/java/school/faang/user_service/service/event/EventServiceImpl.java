package school.faang.user_service.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.event.EventDto;
import school.faang.user_service.dto.event.EventFilterDto;
import school.faang.user_service.dto.skill.SkillDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.entity.User;
import school.faang.user_service.entity.event.Event;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.exception.EntityNotFoundException;
import school.faang.user_service.filter.event_filter.EventFilter;
import school.faang.user_service.mapper.EventMapper;
import school.faang.user_service.mapper.SkillMapper;
import school.faang.user_service.repository.UserRepository;
import school.faang.user_service.repository.event.EventRepository;
import school.faang.user_service.validator.event.EventValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final EventValidator eventValidator;
    private final EventMapper eventMapper;
    private final List<EventFilter> eventFilters;
    private final UserRepository userRepository;
    private final SkillMapper skillMapper;

    @Override
    public EventDto create(EventDto event) {

        validation(event);

        return eventMapper.toDto(eventRepository.save(eventMapper.toEntity(event)));
    }

    @Override
    public EventDto getEvent(long eventId) {

        Optional<Event> event = eventRepository.findById(eventId);

        if (event.isPresent()) {

            return eventMapper.toDto(event.get());
        } else {

            throw new EntityNotFoundException("Not found event with this Id");
        }
    }

    @Override
    public List<EventDto> getEventsByFilter(EventFilterDto filterDto) {

        Stream<Event> list = eventRepository.findAll().stream();

        eventFilters.stream()
                .filter(filter -> filter.isApplicable(filterDto))
                .forEach(filter -> filter.apply(list, filterDto));

        return list.map(eventMapper::toDto).toList();
    }

    @Override
    public EventDto deleteEvent(long eventId) {

        Optional<Event> event = eventRepository.findById(eventId);

        if (event.isPresent()) {

            eventRepository.delete(event.get());
            return eventMapper.toDto(event.get());
        } else {

            throw new EntityNotFoundException("Not found event with this Id");
        }
    }

    @Override
    public EventDto updateEvent(EventDto event) {

        validation(event);

        return eventMapper.toDto(eventRepository.save(eventMapper.toEntity(event)));
    }

    @Override
    public List<EventDto> getOwnedEvents(long userId) {

        List<Event> list = eventRepository.findAllByUserId(userId);

        return list.stream().map(eventMapper::toDto).toList();
    }

    @Override
    public List<EventDto> getParticipatedEvents(long userId) {

        List<Event> list = eventRepository.findParticipatedEventsByUserId(userId);

        return list.stream().map(eventMapper::toDto).toList();
    }

    private void validation(EventDto event) {
        eventValidator.validateEvent(event);

        User user = userRepository.findById(event.getOwnerId()).get();
        List<SkillDto> skills = user.getSkills().stream().map(skillMapper::toDto).toList();

        event.getRelatedSkills().forEach(x -> {
            if (!skills.contains(x)) throw new DataValidationException("You can't create event while you don't have all skills");
        });
    }
}
