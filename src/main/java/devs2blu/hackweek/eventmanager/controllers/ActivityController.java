package devs2blu.hackweek.eventmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.services.ActivityService;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private ActivityService activityService;
    
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/activities")
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest aRequest) throws Exception {
        return ResponseEntity.ok(this.activityService.createActivity(aRequest));
    }
}
