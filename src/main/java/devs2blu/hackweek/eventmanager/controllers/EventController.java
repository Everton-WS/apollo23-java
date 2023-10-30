package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events Management", description = "Endpoints for managing Events")
public class EventController {
    final EventService eventService;

    @Operation(summary = "Get all Events", description = "Returns a List of Events",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<EventResponse> allEvents = eventService.getAllEvents();
        return ResponseEntity.ok().body(allEvents);
    }

    @Operation(summary = "Find Event by ID", description = "Returns a single Event",
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

    @Operation(summary = "Get All Activities From Event", description = "Returns a List of Activities from Event",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityResponse>> findActivitiesByEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getActivitiesByEventId(id));
    }

    @Operation(summary = "Get All Users From Event", description = "Returns a List of Users from Event",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserResponse>> findUsersByEvent(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.eventService.getUsersByEventId(id));
    }

    @Operation(summary = "Get All Treasures From Event", description = "Returns a List of Treasures from Event",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}/treasures")
    public ResponseEntity<List<TreasureResponse>> findTreasuresByEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getTreasuresByEvent(id));
    }

    @Operation(summary = "Get All Questions From Event", description = "Returns a List of Questions from Event",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponse>> findQuestionsByEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.getQuestionsByEventId(id));
    }

    @Operation(summary = "Create New Event",
        responses = {
            @ApiResponse(responseCode = "201", description = "Event Successfully created"),
            @ApiResponse(responseCode = "400", description = "Event couldn't be created")
    })
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest eRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.eventService.createEvent(eRequest));
    }

    @Operation(summary = "Update an Event",
        responses = {
            @ApiResponse(responseCode = "201", description = "Event Successfully created"),
            @ApiResponse(responseCode = "400", description = "Event couldn't be created")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@RequestBody EventRequest eRequest, Long id) {
        return ResponseEntity.ok(this.eventService.updateEvent(eRequest, id));
    }

    @Operation(summary = "Delete Event",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successful operation"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }
}
