package devs2blu.hackweek.eventmanager.dtos.treasure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreasureRequest {
    @NotNull(message = "O score não pode ser nulo")
    @NotBlank(message = "O score não pode estar em branco")
    private Integer score;

    @NotNull(message = "O token não pode ser nulo")
    @NotBlank(message = "O token não pode estar em branco")
    private String token;

    private Long eventId;
    private Long activityId;

    private Boolean hidden;
}
