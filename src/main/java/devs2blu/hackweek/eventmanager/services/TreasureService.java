package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureRequest;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.Treasure;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.TreasureRepository;
import devs2blu.hackweek.eventmanager.utils.UpdateEntities;
import devs2blu.hackweek.eventmanager.utils.mappers.ActivityMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.EventMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.TreasureMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TreasureService {
    
    private final TreasureRepository treasureRepository;
    private final EventRepository eventRepository;
    private final ActivityRepository activityRepository;
    private final TreasureMapper treasureMapper;
    private final EventMapper eventMapper;
    private final ActivityMapper activityMapper;
    private final UserMapper userMapper;

    public List<TreasureResponse> findAllTreasures() {
        return treasureMapper.toResponseList(this.treasureRepository.findAll());
    }

    public TreasureResponse findTreasureById(Long id) throws Exception {
        var t = this.treasureRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.TREASURE_NOT_FOUND));

        return treasureMapper.toResponse(t);
    }

    public EventResponse findTreasureEvent(Long id) throws Exception {
        var t = this.treasureRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.TREASURE_NOT_FOUND));        

        return eventMapper.toResponse(t.getEvent());
    }

    public ActivityResponse findTreasureActivity(Long id) throws Exception {
        var t = this.treasureRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.TREASURE_NOT_FOUND));

        return activityMapper.toResponse(t.getActivity());
    }

    public List<UserResponse> findUsersByTreasure(Long id) throws Exception {
        var t = this.treasureRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.TREASURE_NOT_FOUND));

        return userMapper.toResponseList(t.getUsers());
    }

    public TreasureResponse createTreasure(TreasureRequest tRequest) throws Exception {
        var e = this.eventRepository.findById(tRequest.getEventId()).orElseThrow(() -> new Exception(ErrorMessages.EVENT_NOT_FOUND));
        var activity = this.activityRepository.findById(tRequest.getActivityId()).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        var aEvent = e.getActivities().stream().filter(a -> Objects.equals(a.getId(), activity.getId())).findFirst();

        if (aEvent.isEmpty()) throw new Exception("Activity is not part of Event");

        var newTreasure = this.treasureRepository.save(treasureMapper.toEntity(tRequest));

        return treasureMapper.toResponse(newTreasure);
    }

    public TreasureResponse updateTreasure(TreasureRequest tRequestUpdated, Long id) throws Exception {
        var t = this.treasureRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.TREASURE_NOT_FOUND));

        Treasure updatedTreasure = UpdateEntities.updateTreasure(tRequestUpdated, t);

        var newUpdatedT = this.treasureRepository.save(updatedTreasure);

        return treasureMapper.toResponse(newUpdatedT);
    }

    public void deleteTreasure(Long id) {
        if (!treasureRepository.existsById(id)) {
            throw new EntityNotFoundException((ErrorMessages.TREASURE_NOT_FOUND));
        }
        treasureRepository.deleteById(id);
    }
}
