package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @OneToMany
    private List<Question> questions;

    @OneToMany(mappedBy = "activity")
    private List<Treasure> treasures;

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "activities")
    private List<User> users;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time", columnDefinition = "time")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "time")
    private LocalTime endTime;

    @Column(nullable = false)
    private String location;
}
