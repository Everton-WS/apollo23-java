package devs2blu.hackweek.eventmanager.services;

import org.springframework.stereotype.Service;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureRequest;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.TreasureRepository;
import devs2blu.hackweek.eventmanager.utils.mappers.ActivityMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.EventMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.TreasureMapper;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreasureService {
    
    private final TreasureRepository treasureRepository;
    private final EventRepository eventRepository;
    private final ActivityRepository activityRepository;
    private final TreasureMapper treasureMapper;
    private final EventMapper eventMapper;
    private final ActivityMapper activityMapper;

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

    public TreasureResponse createTreasure(TreasureRequest tRequest) throws Exception {
        System.out.println(tRequest.getEventId());
        var e = this.eventRepository.findById(tRequest.getEventId()).orElseThrow(() -> new Exception(ErrorMessages.EVENT_NOT_FOUND));
        var activity = this.activityRepository.findById(tRequest.getActivityId()).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));

        var aEvent = e.getActivities().stream().filter((a) -> a.getId() == activity.getId()).findFirst();

        if (!aEvent.isPresent()) throw new Exception("Activity is not part of Event");

        var newTreasure = this.treasureRepository.save(treasureMapper.toEntity(tRequest));

        return treasureMapper.toResponse(newTreasure);
    }

    public TreasureResponse deleteTreasure(Long id) throws Exception {
        var t = this.treasureRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessages.TREASURE_NOT_FOUND));

        this.treasureRepository.delete(t);

        return treasureMapper.toResponse(t);
    }
}
