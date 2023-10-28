package devs2blu.hackweek.eventmanager.dtos.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    @NotNull(message = "O Id do evento não pode ser nulo")
    @JsonProperty("event_id")
    private Long eventId;

    @NotNull(message = "O Id da atividade não pode ser nulo")
    @JsonProperty("activity_id")
    private Long activityId;

    @NotNull(message = "O Id do palestrante não pode ser nulo")
    @JsonProperty("speaker_id")
    private Long speakerId;

    @NotNull(message = "O texto da pergunta não pode ser nulo")
    @NotBlank(message = "O texto da pergunta não pode ser nulo")
    @JsonProperty("question_text")
    private String questionText;


    private Boolean approved;

    private Boolean excluded;
}