package school.faang.user_service.filter.event_filter;

import school.faang.user_service.dto.event.EventFilterDto;
import school.faang.user_service.entity.event.Event;
import school.faang.user_service.filter.Filter;

public interface EventFilter extends Filter<Event, EventFilterDto> {
}
