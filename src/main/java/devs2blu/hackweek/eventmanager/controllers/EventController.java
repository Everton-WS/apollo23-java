package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events Management", description = "Endpoints for managing Events")
public class EventController {
    final EventService eventService;

    @Operation(summary = "Get all Events",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
            })
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<EventResponse> allEvents = eventService.getAllEvents();
        return ResponseEntity.ok().body(allEvents);
    }

    @Operation(summary = "Find Activity by ID", description = "Returns a single Events",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Activity not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById(@PathVariable Long id) throws EntityNotFoundException {
        EventResponse eventResponse = eventService.getEventById(id);
        if (eventResponse == null) {
            throw new EntityNotFoundException(ErrorMessages.EVENT_NOT_FOUND);
        }
        return ResponseEntity.ok(eventResponse);
    }
}
