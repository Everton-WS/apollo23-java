package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "treasures")
public class Treasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToMany(mappedBy = "treasures")
    private List<User> users;

    private String token;

    @Column(name = "score")
    private Integer score;

    @Column(name = "hidden", columnDefinition = "boolean")
    private Boolean hidden;

    // Lifecycle Callbacks
    @PrePersist
    public void prePersist() {
        hidden = hidden != null && hidden;
    }
}
