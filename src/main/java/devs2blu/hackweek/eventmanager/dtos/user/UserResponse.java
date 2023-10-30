package devs2blu.hackweek.eventmanager.dtos.user;

import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import lombok.*;

import java.util.List;
import java.util.Set;


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
    private List<EventResponse> eventResponses;
}
