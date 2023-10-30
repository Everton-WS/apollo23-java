package devs2blu.hackweek.eventmanager.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("start_date")
    private LocalDate startDate;
}
