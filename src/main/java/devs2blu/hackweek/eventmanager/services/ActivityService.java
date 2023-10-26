package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.entities.Speaker;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.SpeakerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private ActivityRepository activityRepository;
    private EventRepository eventRepository;
    private SpeakerRepository speakerRepository;

    public ActivityResponse createActivity(ActivityRequest aRequest) {
        Event e = eventRepository.findById(aRequest.getEventId()).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.EVENT_NOT_FOUND));
        Speaker s = speakerRepository.findById(aRequest.getSpeakerId()).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.SPEAKER_NOT_FOUND));

        Activity aEntity = Activity.builder()
            .name(aRequest.getName())
            .location(aRequest.getLocation())
            .event(e)
            .speaker(s)
            .date(Timestamp.valueOf(aRequest.getDate()))
            .startTime(Timestamp.valueOf(aRequest.getStartTime()))
            .endTime(Timestamp.valueOf(aRequest.getEndTime())).build();

        var newActivity = this.activityRepository.save(aEntity);

        return ActivityResponse.builder()
            .id(newActivity.getId())
            .name(newActivity.getName())
            .eventId(e.getId())
            .speakerId(s.getId())
            .date(newActivity.getDate().toLocalDateTime())
            .startTime(newActivity.getStartTime().toLocalDateTime())
            .endTime(newActivity.getEndTime().toLocalDateTime())
            .build();
            
    }
}
