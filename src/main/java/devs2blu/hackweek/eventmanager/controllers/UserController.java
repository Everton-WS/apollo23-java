package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserRequest;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.services.UserRelationService;
import devs2blu.hackweek.eventmanager.services.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Endpoints for managing Users")
public class UserController {
    final UserService userService;
    final UserRelationService userRelationService;

    @Operation(summary = "Get all Users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
            })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllEvents() {
        List<UserResponse> allUsers = userService.getAllUsersWithEvents();
        return ResponseEntity.ok().body(allUsers);
    }

    @Operation(summary = "Find User by ID", description = "Returns a single User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) throws EntityNotFoundException {
        UserResponse userResponse = userService.getUserById(id);
        if (userResponse == null) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventResponse>> getEventsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserEvents(id));
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserActivities(id));
    }

    @GetMapping("/{id}/treasures")
    public ResponseEntity<List<TreasureResponse>> getTreasuresByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserTreasures(id));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserQuestions(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.userService.getUserByEmail(email));
    }

        @GetMapping("/{id}/treasures/total")
    public ResponseEntity<Integer> getTreasuresScoreByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserScore(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest uRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(uRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }

    @GetMapping("/{userId}/events/{eventId}/add")
    public ResponseEntity<Boolean> addEventToUser(@PathVariable Long userId, @PathVariable Long eventId) throws Exception {
        return ResponseEntity.ok(this.userRelationService.addEvent(userId, eventId));
    }

    @GetMapping("/{userId}/activities/{activityId}/add")
    public ResponseEntity<Boolean> addActivityToUser(@PathVariable Long userId, @PathVariable Long activityId) throws Exception {
        return ResponseEntity.ok(this.userRelationService.addActivity(userId, activityId));
    }

    @GetMapping("/{userId}/treasures/{treasureId}/add")
    public ResponseEntity<Boolean> addTreasureToUser(@PathVariable Long userId, @PathVariable Long treasureId) throws Exception {
        return ResponseEntity.ok(this.userRelationService.addTreasure(userId, treasureId));
    }
}