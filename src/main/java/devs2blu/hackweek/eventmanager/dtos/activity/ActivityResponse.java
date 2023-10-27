package devs2blu.hackweek.eventmanager.dtos.activity;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
    private Long id;
    private Long speakerId;
    private Long eventId;
    private String type;
    private String name;
    private String description;
    private LocalDateTime date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
}
