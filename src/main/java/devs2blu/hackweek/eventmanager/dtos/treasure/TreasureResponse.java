package devs2blu.hackweek.eventmanager.dtos.treasure;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("event_id")
    private Long eventId;
    @JsonProperty("activity_id")
    private Long activityId;
}
