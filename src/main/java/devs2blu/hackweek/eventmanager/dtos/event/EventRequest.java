package devs2blu.hackweek.eventmanager.dtos.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String name;
    private String website;
    private String city;
    private String state;
    private LocalDateTime endDate;
    private LocalDateTime startDate;
}
