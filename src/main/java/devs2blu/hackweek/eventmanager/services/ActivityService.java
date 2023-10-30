package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.entities.Speaker;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.SpeakerRepository;
import devs2blu.hackweek.eventmanager.utils.UpdateEntities;
import devs2blu.hackweek.eventmanager.utils.mappers.*;
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
    private final QuestionMapper questionMapper;
    private final TreasureMapper treasureMapper;
    private final UserMapper userMapper;

    public List<ActivityResponse> findAllActivitiesWithSpeakers() {
        List<Activity> activities = activityRepository.findAllActivitiesWithSpeakers();
        return activities.stream()
                .map(activityMapper::toResponseWithSpeaker)
                .toList();
    }

    public ActivityResponse findById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ACTIVITY_NOT_FOUND));
        return activityMapper.toResponseWithSpeaker(activity);
    }

    public EventResponse findEventActivity(Long activityId) throws Exception {

        var a = this.activityRepository.findById(activityId).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        return this.eventMapper.toResponse(a.getEvent());
    }

    public SpeakerResponse findSpeakerActivity(Long activityId) throws Exception {
        var a = this.activityRepository.findById(activityId).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        return speakerMapper.toResponse(a.getSpeaker());
    }

    public List<QuestionResponse> findQuestionsByActivityId(Long id) throws Exception {
        var a = this.activityRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));      
        
        return questionMapper.toResponseList(a.getQuestions());
    }

    public List<TreasureResponse> findTreasuresByActivityId(Long id) throws Exception {
        var a = this.activityRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));      
        
        return treasureMapper.toResponseList(a.getTreasures());
    }


    public List<UserResponse> findUsersByActivityId(Long id) throws Exception {
        var a = this.activityRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));      
        
        return userMapper.toResponseList(a.getUsers());
    }

    public ActivityResponse createActivity(ActivityRequest aRequest) {
        Event e = eventRepository.findById(aRequest.getEventId()).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.EVENT_NOT_FOUND));
        Optional<Speaker> s = speakerRepository.findById(aRequest.getSpeakerId());

        Activity aEntity = activityMapper.toEntity(aRequest);
        aEntity.setEvent(e);
        s.ifPresent(aEntity::setSpeaker);

        var newActivity = activityRepository.save(aEntity);

        return activityMapper.toResponse(newActivity);
            
    }

    public ActivityResponse updateActivity(ActivityRequest aRequest, Long id) throws Exception {
        var a = this.activityRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND)); 

        var updateActivity = UpdateEntities.updateActivity(aRequest, a);

        return activityMapper.toResponse(this.activityRepository.save(updateActivity));
    }

    public void deleteActivity(Long activityId) {
        if (!activityRepository.existsById(activityId)) {
            throw new EntityNotFoundException(ErrorMessages.ACTIVITY_NOT_FOUND);
        }
        eventRepository.deleteById(activityId);
    }
}
