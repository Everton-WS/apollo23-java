package devs2blu.hackweek.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Creates the join table without the need to create a separate entity
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch=FetchType.EAGER)
    @JoinTable(
            name = "users_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

        @ManyToMany(
            cascade = CascadeType.ALL,
            fetch=FetchType.EAGER)
    @JoinTable(
            name = "users_activities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> activities;

        @ManyToMany(
                cascade = CascadeType.ALL,
                fetch=FetchType.EAGER)
        @JoinTable(
                name = "users_treasures",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "treasure_id")
        )
    private List<Treasure> treasures;

    @JsonIgnore
    @OneToMany
    private List<Question> questions;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String mobile;
}
