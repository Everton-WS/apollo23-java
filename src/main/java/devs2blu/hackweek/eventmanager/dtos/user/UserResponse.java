package devs2blu.hackweek.eventmanager.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    @JsonProperty("events")
    private List<EventResponse> eventResponses;
}
