package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    private Set<Event> events = new HashSet<>();

    @Column(nullable = false)
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String mobile;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", events=" + events +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
