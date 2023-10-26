package devs2blu.hackweek.eventmanager.dtos.event;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
public class EventRequest {
    private String name;
    private String website;
    private String city;
    private String state;
    private LocalDate endDate;
    private LocalDate startDate;
}
