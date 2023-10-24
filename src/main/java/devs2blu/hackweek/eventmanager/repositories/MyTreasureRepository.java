package devs2blu.hackweek.eventmanager.repositories;

import devs2blu.hackweek.eventmanager.entities.MyTreasure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyTreasureRepository extends JpaRepository<MyTreasure, Long> {
}
