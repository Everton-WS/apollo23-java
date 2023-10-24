package devs2blu.hackweek.eventmanager.repositories;

import devs2blu.hackweek.eventmanager.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
