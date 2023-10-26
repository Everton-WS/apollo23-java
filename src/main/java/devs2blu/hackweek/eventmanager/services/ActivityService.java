package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.builders.ActivityBuilder;
import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.entities.Speaker;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.SpeakerRepository;
import devs2blu.hackweek.eventmanager.utils.mappers.ActivityMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.EventMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.SpeakerMapper;
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
    private final ActivityMapper activityMapper;
    private final EventMapper eventMapper;
    private final SpeakerMapper speakerMapper;

    public List<ActivityResponse> findAllActivities() {
        return activityMapper.toResponseList(this.activityRepository.findAll());
    }

    public ActivityResponse findActivityById(Long id) throws Exception {
        var a = this.activityRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        return activityMapper.toResponse(a);
    }

    public EventResponse findEventActivity(Long activityId) throws Exception {

        var a = this.activityRepository.findById(activityId).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        return this.eventMapper.toResponse(a.getEvent());
    }

    public SpeakerResponse findSpeakerActivity(Long activityId) throws Exception {
        var a = this.activityRepository.findById(activityId).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        return speakerMapper.toResponse(a.getSpeaker());
    }

    public ActivityResponse createActivity(ActivityRequest aRequest) {
        Event e = eventRepository.findById(aRequest.getEventId()).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.EVENT_NOT_FOUND));
        Optional<Speaker> s = speakerRepository.findById(aRequest.getSpeakerId());

        Activity aEntity = ActivityBuilder.activityRequestToActivityEntity(aRequest);
        aEntity.setEvent(e);
        if (s.isPresent()) aEntity.setSpeaker(s.get());

        var newActivity = activityRepository.save(aEntity);

        return activityMapper.toResponse(newActivity);
            
    }

    public ActivityResponse deleteActivity(Long activityId) throws Exception {
        var a = this.activityRepository.findById(activityId).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        this.activityRepository.delete(a);

        return activityMapper.toResponse(a);
    }
}
