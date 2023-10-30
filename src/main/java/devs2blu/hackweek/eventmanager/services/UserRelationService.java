package devs2blu.hackweek.eventmanager.services;

import org.springframework.stereotype.Service;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.repositories.ActivityRepository;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.TreasureRepository;
import devs2blu.hackweek.eventmanager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRelationService {
    
    final private UserRepository userRepository;
    final private EventRepository eventRepository;
    final private ActivityRepository activityRepository;
    final private TreasureRepository treasureRepository;
    
    public Boolean addEvent(Long userId, Long eventId) throws Exception {
        var e = this.eventRepository.findById(eventId).orElseThrow(() -> new Exception(ErrorMessages.EVENT_NOT_FOUND));
        var u = this.userRepository.findById(userId).orElseThrow(() -> new Exception(ErrorMessages.USER_NOT_FOUND));

        if (u.getEvents().stream().filter((event) -> event.getId() == eventId).findFirst() != null) return false;

        var events = u.getEvents();
        events.add(e);

        u.setEvents(events);

        this.userRepository.save(u);

        return true;
    }

    public Boolean addActivity(Long userId, Long activityId) throws Exception {
        var a = this.activityRepository.findById(activityId).orElseThrow(() -> new Exception(ErrorMessages.ACTIVITY_NOT_FOUND));
        var u = this.userRepository.findById(userId).orElseThrow(() -> new Exception(ErrorMessages.USER_NOT_FOUND));

        if (u.getActivities().stream().filter((activity) -> activity.getId() == activityId).findFirst() != null) return false;

        var activities = u.getActivities();
        activities.add(a);

        u.setActivities(activities);

        this.userRepository.save(u);

        return true;
        
    }

    public Boolean addTreasure(Long userId, Long treasureId) throws Exception {
        var t = this.treasureRepository.findById(treasureId).orElseThrow(() -> new Exception(ErrorMessages.TREASURE_NOT_FOUND));
        var u = this.userRepository.findById(userId).orElseThrow(() -> new Exception(ErrorMessages.USER_NOT_FOUND));

        if (u.getTreasures().stream().filter((treasure) -> treasure.getId() == treasureId).findFirst() != null) return false;

        var treasures = u.getTreasures();
        treasures.add(t);

        u.setTreasures(treasures);

        this.userRepository.save(u);

        return true;
    }
}
