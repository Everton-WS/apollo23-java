package devs2blu.hackweek.eventmanager.repositories;

import devs2blu.hackweek.eventmanager.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByEventId(Long id);
}
