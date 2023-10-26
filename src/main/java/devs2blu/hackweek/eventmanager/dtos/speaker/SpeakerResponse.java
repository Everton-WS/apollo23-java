package devs2blu.hackweek.eventmanager.dtos.speaker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeakerResponse {
    private Long id;
    private String name;
    private String miniBio;
    private Long eventId;
}
