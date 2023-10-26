package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.entities.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper extends AbstractMapper<Event, EventRequest, EventResponse> {
    public EventMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Event> getEntityClass() {
        return Event.class;
    }

    @Override
    Class<EventRequest> getRequestClass() {
        return EventRequest.class;
    }

    @Override
    Class<EventResponse> getResponseClass() {
        return EventResponse.class;
    }
}
