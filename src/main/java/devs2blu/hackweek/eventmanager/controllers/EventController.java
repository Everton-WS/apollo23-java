package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/events")
@RequiredArgsConstructor
public class EventController {
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<EventResponse> allEvents = eventService.getAllEvents();
        return ResponseEntity.ok().body(allEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById(@PathVariable Long id) throws Exception {
        EventResponse eventResponse = eventService.getEventById(id);
        if (eventResponse == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return ResponseEntity.ok(eventResponse);
    }
}
