package devs2blu.hackweek.eventmanager.dtos.question;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUserResponse {
    private Long id;
    private String name;
    private String email;
}