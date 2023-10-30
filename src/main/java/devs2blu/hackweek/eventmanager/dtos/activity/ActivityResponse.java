package devs2blu.hackweek.eventmanager.dtos.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
    private Long id;
    @JsonProperty("speaker")
    private SpeakerResponse speakerResponse;
    @JsonProperty("event_id")
    private Long eventId;
    private String type;
    private String name;
    private String description;
    private LocalDate date;
    @JsonProperty("start_time")
    private LocalTime startTime;
    @JsonProperty("end_time")
    private LocalTime endTime;
    private String location;
}
