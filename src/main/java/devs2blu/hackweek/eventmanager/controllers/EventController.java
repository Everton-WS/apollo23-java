package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Operation(summary = "Find Event by ID", description = "Returns a single Events",
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

    @Operation(summary = "Get All Activities From Event",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityResponse>> findActivitiesByEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getActivitiesByEventId(id));
    }

    @Operation(summary = "Get All Users From Event",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserResponse>> findUsersByEvent(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.eventService.getUsersByEventId(id));
    }

    @Operation(summary = "Get All Treasures From Event",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}/treasures")
    public ResponseEntity<List<TreasureResponse>> findTreasuresByEvent(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.eventService.getTreasuresByEvent(id));
    }


    @Operation(summary = "Create New Event",
        responses = {
            @ApiResponse(responseCode = "201", description = "Event Successfully created"),
            @ApiResponse(responseCode = "400", description = "Event could'nt be created")
    })
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest eRequest) {
        System.out.println(eRequest.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.eventService.createEvent(eRequest));
    }

    @Operation(summary = "Delete Event",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponse> deleteEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.deleteEventById(id));
    }


}
