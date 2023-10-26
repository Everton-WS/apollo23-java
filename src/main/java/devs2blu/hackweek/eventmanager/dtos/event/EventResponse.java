package devs2blu.hackweek.eventmanager.dtos.event;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter @Builder
public class EventResponse {
    private Long id;
    private String name;
    private String website;
    private String city;
    private String state;
    private LocalDateTime endDate;
    private LocalDateTime startDate;
}
