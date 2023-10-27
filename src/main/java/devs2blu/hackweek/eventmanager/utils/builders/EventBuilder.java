package devs2blu.hackweek.eventmanager.utils.builders;

import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.entities.Event;

public class EventBuilder {
    
    public static Event eventRequestToEventEntity(EventRequest eRequest) {
        return Event.builder()
                .name(eRequest.getName())
                .website(eRequest.getWebsite())
                .city(eRequest.getCity())
                .state(eRequest.getState())
                .startDate(eRequest.getStartDate())
                .endDate(eRequest.getEndDate())
                .build();
    }

    public static EventResponse eventEntityToEventResponse(Event e) {
        return EventResponse.builder()
            .id(e.getId())
            .name(e.getName())
            .city(e.getCity())
            .state(e.getState())
            .website(e.getWebsite())
            .startDate(e.getStartDate())
            .endDate(e.getEndDate())
            .build();
    }
}
