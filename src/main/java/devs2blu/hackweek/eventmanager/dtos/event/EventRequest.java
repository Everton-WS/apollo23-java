package devs2blu.hackweek.eventmanager.dtos.event;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class EventRequest {
    private String name;

    private String website;

    private String city;

    private String state;

    private LocalDateTime endDate;

    private LocalDateTime startDate;
}
