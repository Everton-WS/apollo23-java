package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
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
    private Set<User> users = new HashSet<>();

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "start_date")
    private Timestamp startDate;

    @OneToMany(mappedBy = "event")
    private Set<Activity> activities = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Message> messages = new HashSet<>();
}
