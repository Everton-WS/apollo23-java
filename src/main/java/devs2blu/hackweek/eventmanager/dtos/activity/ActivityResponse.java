package devs2blu.hackweek.eventmanager.dtos.activity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ActivityResponse {

    private Long id;

    private Long eventId;

    private Long speakerId;

    private String type;
    private String name;

    private LocalDateTime date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String location;
}
