package devs2blu.hackweek.eventmanager.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.services.EventService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/events")
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
