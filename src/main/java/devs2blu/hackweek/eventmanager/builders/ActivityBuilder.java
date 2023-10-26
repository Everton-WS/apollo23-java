package devs2blu.hackweek.eventmanager.builders;

import java.sql.Timestamp;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;

public class ActivityBuilder {
    
    public static Activity activityRequestToActivityEntity(ActivityRequest aRequest) {
        return Activity.builder()
            .name(aRequest.getName())
            .description(aRequest.getDescription())
            .type(aRequest.getType())
            .startTime(aRequest.getStartTime())
            .endTime(aRequest.getEndTime())
            .date(aRequest.getDate())
            .build();
    }

    public static ActivityResponse activityEntityToActivityResponse(Activity aEntity) {
        return ActivityResponse.builder()
            .id(aEntity.getId())
            .name(aEntity.getName())
            .description(aEntity.getDescription())
            .type(aEntity.getType())
            .startTime(aEntity.getStartTime())
            .endTime(aEntity.getEndTime())
            .eventId(aEntity.getEvent().getId())
            .speakerId(aEntity.getSpeaker().getId())
            .location(aEntity.getLocation())
            .build();
    }
}
