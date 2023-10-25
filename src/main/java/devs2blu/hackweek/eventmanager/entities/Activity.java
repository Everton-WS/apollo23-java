package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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

    private String type;
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "start_time", columnDefinition = "time")
    private Timestamp startTime;

    @Column(name = "end_time", columnDefinition = "time")
    private Timestamp endTime;

    private String location;
}
