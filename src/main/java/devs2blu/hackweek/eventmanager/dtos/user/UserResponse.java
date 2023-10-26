package devs2blu.hackweek.eventmanager.dtos.user;

import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private List<EventResponse> events;
}
