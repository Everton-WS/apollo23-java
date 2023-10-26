package devs2blu.hackweek.eventmanager.dtos.myTreasure;

import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyTreasureResponse {
    private Long id;
    private Integer score;
    private TreasureResponse treasure;
    private EventResponse event;
}
