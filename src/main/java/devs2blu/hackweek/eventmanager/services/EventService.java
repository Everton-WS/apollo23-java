package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.builders.ActivityBuilder;
import devs2blu.hackweek.eventmanager.builders.EventBuilder;
import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    final EventRepository eventRepository;
    final ActivityRepository activityRepository;

    public List<EventResponse> getAllEvents() {
        List<Event> events =  this.eventRepository.findAll();

        return events.stream().map(EventBuilder::eventEntityToEventResponse).toList();
    }

    public EventResponse getEventById(Long id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));

        return EventBuilder.eventEntityToEventResponse(e);
    }

    public EventResponse createEvent(EventRequest eRequest) {
        Event e = EventBuilder.eventRequestToEventEntity(eRequest);

        Event newEvent = this.eventRepository.save(e);

        return EventBuilder.eventEntityToEventResponse(newEvent);
    }

    public EventResponse deleteEventById(Long id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));

        this.eventRepository.delete(e);

        return EventBuilder.eventEntityToEventResponse(e);
    }

    public List<ActivityResponse> getActivitiesByEventId(Long id) {
        List<Activity> activities = this.activityRepository.findAllByEventId(id);

        return activities.stream().map(ActivityBuilder::activityEntityToActivityResponse).toList();
    }
}
