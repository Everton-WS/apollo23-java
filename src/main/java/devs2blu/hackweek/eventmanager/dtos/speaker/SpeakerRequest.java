package devs2blu.hackweek.eventmanager.dtos.speaker;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeakerRequest {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser vazio")
    private String name;

    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
    @JsonProperty("mini_bio")
    private String miniBio;

    @JsonProperty("social_media")
    private String socialMedia;

    @NotNull(message = "O Id do evento não pode ser nulo")
    private Long eventId;
}