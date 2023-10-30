package devs2blu.hackweek.eventmanager.repositories;

import devs2blu.hackweek.eventmanager.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByEventId(Long id);

    @Query("SELECT a FROM Activity a JOIN FETCH a.speaker")
    List<Activity> findAllActivitiesWithSpeakers();
}
