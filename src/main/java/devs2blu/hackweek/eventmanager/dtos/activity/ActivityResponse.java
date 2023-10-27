package devs2blu.hackweek.eventmanager.dtos.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalTime;
import java.time.LocalDate;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
    private Long id;
    private Long speakerId; // TODO -> Abrir para SpeakerResponseDTO
    @JsonProperty("event_id")
    private Long eventId;
    private String type;
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
}
