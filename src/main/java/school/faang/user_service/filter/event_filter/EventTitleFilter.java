package school.faang.user_service.filter.event_filter;

import school.faang.user_service.dto.event.EventFilterDto;
import school.faang.user_service.entity.event.Event;

import java.util.stream.Stream;

public class EventTitleFilter  implements EventFilter{

    @Override
    public boolean isApplicable(EventFilterDto filters) {
        return !filters.getTitlePattern().isEmpty();
    }

    @Override
    public void apply(Stream<Event> stream, EventFilterDto filters) {
        stream.filter(event -> event.getTitle().equals(filters.getTitlePattern()));
    }
}
