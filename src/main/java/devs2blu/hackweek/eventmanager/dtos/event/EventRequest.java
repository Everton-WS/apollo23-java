package devs2blu.hackweek.eventmanager.dtos.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
