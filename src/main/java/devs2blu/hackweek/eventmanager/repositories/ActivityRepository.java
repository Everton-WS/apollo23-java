package devs2blu.hackweek.eventmanager.repositories;

import devs2blu.hackweek.eventmanager.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
