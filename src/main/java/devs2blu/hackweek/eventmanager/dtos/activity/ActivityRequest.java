package devs2blu.hackweek.eventmanager.dtos.activity;

import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
}
