package devs2blu.hackweek.eventmanager.repositories;

import devs2blu.hackweek.eventmanager.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
