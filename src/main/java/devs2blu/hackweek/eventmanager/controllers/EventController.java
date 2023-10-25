package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.services.EventService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BasePathAwareController
public class EventController {

    private EventService eventService;
    
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventResponse> getAllEvents() {
        return this.eventService.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public EventResponse getEventById(@PathVariable Long id) {
        return this.getEventById(id);
    }

    @PostMapping("/events")
    public EventResponse createEvent(@RequestBody EventRequest eRequest) {
        return this.eventService.createEvent(eRequest);
    }

    @DeleteMapping("/events/{id}")
    public EventResponse deleteEventById(@PathVariable Long id) throws Exception {
        return this.eventService.deleteEventById(id);
    }
}
