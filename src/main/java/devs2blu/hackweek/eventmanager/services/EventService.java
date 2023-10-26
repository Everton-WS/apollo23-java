package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.services.builders.Builders;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ActivityRepository activityRepository;

    public List<EventResponse> getAllEvents() {
        List<Event> events =  this.eventRepository.findAll();

        return events.stream().map(e -> {
            return EventResponse.builder()
            .id(e.getId())
            .name(e.getName())
            .city(e.getCity())
            .state(e.getState())
            .website(e.getWebsite())
            .startDate(e.getStartDate().toLocalDateTime())
            .endDate(e.getEndDate().toLocalDateTime()).build();
        }).toList();
    }

    public EventResponse getEventById(Long id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));

        return Builders.eventEntityToEventResponse(e);
    }

    public EventResponse createEvent(EventRequest eRequest) {
        Event e = Builders.eventRequestToEventEntity(eRequest);

        Event newEvent = this.eventRepository.save(e);

        return Builders.eventEntityToEventResponse(newEvent);
    }

    public EventResponse deleteEventById(Long id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));

        this.eventRepository.delete(e);

        return Builders.eventEntityToEventResponse(e);
    }

    public void getActivitiesByEventId(Long id) {
        List<Activity> activities = this.activityRepository.findAllByEventId(id);

        
    }
}
