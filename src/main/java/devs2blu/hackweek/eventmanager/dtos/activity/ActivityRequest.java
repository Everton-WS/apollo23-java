package devs2blu.hackweek.eventmanager.dtos.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @Builder
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
