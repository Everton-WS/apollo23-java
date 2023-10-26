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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Speaker user;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @Column(name = "question_text", columnDefinition = "text")
    private String questionText;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "excluded")
    private Boolean excluded;

    // Lifecycle Callbacks
    @PrePersist
    public void prePersist() {
        if (approved == null) {
            approved = true;
        }
        if (excluded == null) {
            excluded = false;
        }
    }
}
