package devs2blu.hackweek.eventmanager.dtos.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;

    @JsonProperty("question_text")
    private String questionText;

    private Boolean approved;

    private QuestionUserResponse user;
}
