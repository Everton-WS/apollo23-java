package devs2blu.hackweek.eventmanager.dtos.activity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class ActivityResponse {
    private Long id;
    private Long speakerId;
    private Long eventId;
    private String type;
    private String name;
    private LocalDateTime date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
}
