package devs2blu.hackweek.eventmanager.dtos.treasure;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreasureResponse {
    private Long id;
    private Integer score;
    private String token;
    private Boolean hidden;

    private Long eventId;
    private Long activityId;
}
