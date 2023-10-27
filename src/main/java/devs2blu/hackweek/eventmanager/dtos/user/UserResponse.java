package devs2blu.hackweek.eventmanager.dtos.user;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String mobile;
}
