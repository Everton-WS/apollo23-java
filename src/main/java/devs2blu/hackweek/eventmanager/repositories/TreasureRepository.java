package devs2blu.hackweek.eventmanager.repositories;

import devs2blu.hackweek.eventmanager.entities.Treasure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreasureRepository extends JpaRepository<Treasure, Long> {
}
