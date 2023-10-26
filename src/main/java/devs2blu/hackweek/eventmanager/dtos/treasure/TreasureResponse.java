package devs2blu.hackweek.eventmanager.dtos.treasure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreasureResponse {
    private Long id;
    private Integer score;
    private String token;
    private Boolean hidden;
}
