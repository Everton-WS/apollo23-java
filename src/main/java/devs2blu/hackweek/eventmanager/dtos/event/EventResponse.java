package devs2blu.hackweek.eventmanager.dtos.event;

import lombok.*;

import java.time.LocalDate;


@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String name;
    private String website;
    private String city;
    private String state;
    private LocalDate endDate;
    private LocalDate startDate;
}
