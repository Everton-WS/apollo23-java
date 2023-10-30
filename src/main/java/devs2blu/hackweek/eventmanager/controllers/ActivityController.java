package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.services.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
@Tag(name = "Activities Management", description = "Endpoints for managing Activities")
public class ActivityController {

    final ActivityService activityService;

    @Operation(summary = "Get All Activities", description = "Returns a List of Activities",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAllActivities() {
        return ResponseEntity.ok(this.activityService.findAllActivitiesWithSpeakers());
    }

    @Operation(summary = "Get Activity By Id", description = "Returns a Single Activity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.activityService.findById(id));
    } 

    @Operation(summary = "Get Activity's Speaker", description = "Returns a Single Speaker from Activity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @GetMapping("/{id}/speaker")
    public ResponseEntity<SpeakerResponse> getActivitySpeaker(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.activityService.findSpeakerActivity(id));
    } 

    @Operation(summary = "Get Activity's Event", description = "Returns a Single Event From Activity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @GetMapping("{id}/event")
    public ResponseEntity<EventResponse> getActivityEvent(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.activityService.findEventActivity(id));
    }

    @Operation(summary = "Get Activity's Questions", description = "Returns a List of Questions from Activity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByActivity(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.activityService.findQuestionsByActivityId(id));
    }

    @Operation(summary = "Get Activity's Treasures", description = "Returns a List of Treasures from Activity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @GetMapping("/{id}/treasures")
    public ResponseEntity<List<TreasureResponse>> getTreasuresByActivity(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.activityService.findTreasuresByActivityId(id));
    }

    @Operation(summary = "Get Activity's Users", description = "Returns a List of Users from Activity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserResponse>> getUsersByActivity(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.activityService.findUsersByActivityId(id));
    }

    @Operation(summary = "Create Activity",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Activity Created Successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request - Invalid Input"),
            })
    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest aRequest) {
        ActivityResponse createdActivity = activityService.createActivity(aRequest);
        if (createdActivity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Update Activity",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Activity Created Successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request - Invalid Input"),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(@RequestBody ActivityRequest aRequest, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.activityService.updateActivity(aRequest, id));
    }

    @Operation(summary = "Delete Activity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Activity Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
