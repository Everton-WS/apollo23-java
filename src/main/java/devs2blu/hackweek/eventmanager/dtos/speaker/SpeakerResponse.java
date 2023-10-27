package devs2blu.hackweek.eventmanager.dtos.speaker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeakerResponse {
    private Long id;

    @JsonProperty("event_id")
    private Long eventId;

    private String name;

    @JsonProperty("mini_bio")
    private String miniBio;

    @JsonProperty("social_media")
    private String socialMedia;
}
