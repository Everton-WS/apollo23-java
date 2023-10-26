package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.builders.ActivityBuilder;
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
import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final EventRepository eventRepository;
    private final SpeakerRepository speakerRepository;

    public List<ActivityResponse> findAllActivities() {
        return this.activityRepository.findAll().stream().map(ActivityBuilder::activityEntityToActivityResponse).toList();
    }

    public ActivityResponse findActivityById(Long id) throws Exception {
        var a = this.activityRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        return ActivityBuilder.activityEntityToActivityResponse(a);
    }

    public void findEventActivity(Long activityId) {

    }

    public ActivityResponse createActivity(ActivityRequest aRequest) {
        Event e = eventRepository.findById(aRequest.getEventId()).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.EVENT_NOT_FOUND));
        Optional<Speaker> s = speakerRepository.findById(aRequest.getSpeakerId());

        Activity aEntity = ActivityBuilder.activityRequestToActivityEntity(aRequest);
        aEntity.setEvent(e);
        if (s.isPresent()) aEntity.setSpeaker(s.get());

        var newActivity = activityRepository.save(aEntity);

        return ActivityBuilder.activityEntityToActivityResponse(newActivity);
            
    }
}
