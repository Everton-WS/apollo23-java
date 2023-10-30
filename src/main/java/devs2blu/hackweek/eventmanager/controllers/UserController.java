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
        List<UserResponse> allUsers = userService.findAllUsersWithEvents();
        return ResponseEntity.ok().body(allUsers);
    }

    @Operation(summary = "Get User by ID", description = "Returns a single User",
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

    @Operation(summary = "Get User's Events", description = "Return a List of Events from User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventResponse>> getEventsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserEvents(id));
    }

    @Operation(summary = "Get User's Activities'", description = "Returns a List of Activities from User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserActivities(id));
    }

    @Operation(summary = "Get User's Treasures'", description = "Returns a List of Treasures from User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/{id}/treasures")
    public ResponseEntity<List<TreasureResponse>> getTreasuresByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserTreasures(id));
    }

    @Operation(summary = "Get User's Questions", description = "Returns a List of Questions from User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserQuestions(id));
    }

    @Operation(summary = "Get User E-mail", description = "Returns a single E-mail",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.userService.getUserByEmail(email));
    }

    @Operation(summary = "Get User Total Score", description = "Return User's Total Score",
        responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation",
                        content = @Content(schema = @Schema(implementation = UserResponse.class))),
                @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}/treasures/total")
    public ResponseEntity<Integer> getTreasuresScoreByUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUserScore(id));
    }

    @Operation(summary = "Create User",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User Created Successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request - Invalid Input"),
            })
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest uRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(uRequest));
    }

    @Operation(summary = "Update User",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User Updated Successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request - Invalid Input"),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest uRequest) {
        return ResponseEntity.ok(this.userService.updateUser(id, uRequest));
    }

    @Operation(summary = "Delete User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }

    @Operation(summary = "Add Event to User", description = "Returns a Boolean",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            })
    @GetMapping("/{userId}/events/{eventId}/add")
    public ResponseEntity<Boolean> addEventToUser(@PathVariable Long userId, @PathVariable Long eventId) throws Exception {
        return ResponseEntity.ok(this.userRelationService.addEvent(userId, eventId));
    }

    @Operation(summary = "Add Activity to User", description = "Returns a Boolean",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "404", description = "Activity not found")
            })
    @GetMapping("/{userId}/activities/{activityId}/add")
    public ResponseEntity<Boolean> addActivityToUser(@PathVariable Long userId, @PathVariable Long activityId) throws Exception {
        return ResponseEntity.ok(this.userRelationService.addActivity(userId, activityId));
    }

    @Operation(summary = "Add Treasure to User", description = "Returns a Boolean",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "404", description = "Treasure not found")
            })
    @GetMapping("/{userId}/treasures/{treasureId}/add")
    public ResponseEntity<Boolean> addTreasureToUser(@PathVariable Long userId, @PathVariable Long treasureId) throws Exception {
        return ResponseEntity.ok(this.userRelationService.addTreasure(userId, treasureId));
    }
}