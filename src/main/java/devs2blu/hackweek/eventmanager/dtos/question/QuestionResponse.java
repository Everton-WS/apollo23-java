package devs2blu.hackweek.eventmanager.dtos.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;

    @JsonProperty("question_text")
    private String questionText;

    private Boolean approved;

    private UserResponse user;
}
