package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.entities.User;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.utils.UpdateEntities;
import devs2blu.hackweek.eventmanager.utils.mappers.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ActivityRepository activityRepository;
    private final EventMapper eventMapper;
    private final ActivityMapper activityMapper;
    private final UserMapper userMapper;
    private final TreasureMapper treasureMapper;
    private final QuestionMapper questionMapper;

    public List<EventResponse> getAllEvents() {
        List<Event> events = this.eventRepository.findAll();
        return eventMapper.toResponseList(events);
    }

    public EventResponse getEventById(Long id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));

        return eventMapper.toResponse(e);
    }

    public EventResponse createEvent(EventRequest eRequest) {
        Event e = eventMapper.toEntity(eRequest);

        Event newEvent = this.eventRepository.save(e);

        return eventMapper.toResponse(newEvent);
    }

    public EventResponse updateEvent(EventRequest eRequest, Long id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));
        var updatedEvent = UpdateEntities.updateEvent(eRequest, e);

        return eventMapper.toResponse(this.eventRepository.save(updatedEvent));
    }

    public void deleteEventById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.EVENT_NOT_FOUND);
        }
        eventRepository.deleteById(id);
    }

    public List<ActivityResponse> getActivitiesByEventId(Long id) {
        List<Activity> activities = this.activityRepository.findAllByEventId(id);

        return activityMapper.toResponseList(activities);
    }

    public List<UserResponse> getUsersByEventId(Long id) throws Exception {
        final List<User> users = this.eventRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.USER_NOT_FOUND)).getUsers();


        return userMapper.toResponseList(users);
    }

    public List<QuestionResponse> getQuestionsByEventId(Long id) {
       Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));

       return questionMapper.toResponseList(e.getQuestions());
    }

    public List<TreasureResponse> getTreasuresByEvent(Long id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.ID_NOT_FOUND));

        return treasureMapper.toResponseList(e.getTreasures());
    }

}
