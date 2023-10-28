package devs2blu.hackweek.eventmanager.dtos.activity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
    private Long eventId;
    private Long speakerId;
    private String type;
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
}
