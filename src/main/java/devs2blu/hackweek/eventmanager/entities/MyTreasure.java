package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "my_treasures")
public class MyTreasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "treasure_id")
    private Treasure treasure;

    @Column(name = "score")
    private Integer score;

    @Column(name = "date_time")
    private Timestamp dateTime;

    // Lifecycle Callbacks
    @PrePersist
    public void prePersist() {
        dateTime = Timestamp.valueOf(LocalDateTime.now());
    }
}
