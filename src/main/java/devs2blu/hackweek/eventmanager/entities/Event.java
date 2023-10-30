package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "events")
    private List<User> users;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @OneToMany(mappedBy = "event")
    private Set<Activity> activities;

    @OneToMany(mappedBy = "event")
    private List<Question> questions;

    @OneToMany(mappedBy = "event")
    private List<Treasure> treasures;
}
