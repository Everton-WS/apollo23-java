package devs2blu.hackweek.eventmanager.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Question ID", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @Column(name = "question_text", columnDefinition = "text")
    @Schema(name = "Question text", example = "What's your question?")
    private String questionText;

    @Column(name = "approved")
    @ColumnDefault("true")
    @Schema(name = "Approved", example = "true")
    private Boolean approved;

    @Column(name = "excluded")
    @ColumnDefault("false")
    @Schema(name = "Excluded", example = "false")
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
