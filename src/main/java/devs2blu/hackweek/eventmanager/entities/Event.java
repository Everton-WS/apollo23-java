package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String website;

    private String city;

    private String state;

    @ManyToMany(mappedBy = "events")
    private Set<User> users;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "start_date")
    private Timestamp startDate;

    @OneToMany(mappedBy = "event")
    private List<Activity> activities;

    @OneToMany(mappedBy = "event")
    private List<Message> messages;
}
