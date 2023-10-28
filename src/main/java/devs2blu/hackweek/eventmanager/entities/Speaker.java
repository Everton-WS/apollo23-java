package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false)
    private String name;

    @Column(name = "social_media")
    private String socialMedia;

    @Column(name = "mini_bio", columnDefinition = "text")
    private String miniBio;
}

