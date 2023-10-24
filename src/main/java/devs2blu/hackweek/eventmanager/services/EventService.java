package devs2blu.hackweek.eventmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.services.builders.Builders;

@Service
public class EventService {

    private EventRepository eventRepository;
    private ActivityRepository activityRepository;
    
    

    public EventService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<EventResponse> getAllEvents() {
        List<Event> events =  this.eventRepository.findAll();

        List<EventResponse> eResponses = events.stream().map((e) -> {
            return EventResponse.builder()
            .id(e.getId())
            .name(e.getName())
            .city(e.getCity())
            .state(e.getState())
            .website(e.getWebsite())
            .startDate(e.getStartDate().toLocalDateTime())
            .endDate(e.getEndDate().toLocalDateTime()).build();
        }).toList();

        return eResponses;
    }

    public EventResponse getEventById(Long id) throws Exception {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new Exception("Id Not Found"));

        EventResponse eResponse = Builders.eventEntityToEventResponse(e);

        return eResponse;
    }

    public EventResponse createEvent(EventRequest eRequest) {
        Event e = Builders.eventRequestToEventEntity(eRequest);

        Event newEvent = this.eventRepository.save(e);

        return Builders.eventEntityToEventResponse(newEvent);
    }

    public EventResponse deleteEventById(Long id) throws Exception {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new Exception("Id Not Found"));

        this.eventRepository.delete(e);

        return Builders.eventEntityToEventResponse(e);
    }

    public void getActivitiesByEventId(Long id) {
        List<Activity> activities = this.activityRepository.findAllByEventId(id);

        
    }
}
