package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.services.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
@Tag(name = "Activities Management", description = "Endpoints for managing Activities")
public class ActivityController {
    private final ActivityService activityService;

    @Operation(summary = "Create an Activity",
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
}
