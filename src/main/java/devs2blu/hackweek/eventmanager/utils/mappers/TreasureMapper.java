package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureRequest;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.entities.Treasure;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TreasureMapper extends AbstractMapper<Treasure, TreasureRequest, TreasureResponse> {
    public TreasureMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Treasure> getEntityClass() {
        return Treasure.class;
    }

    @Override
    Class<TreasureRequest> getRequestClass() {
        return TreasureRequest.class;
    }

    @Override
    Class<TreasureResponse> getResponseClass() {
        return TreasureResponse.class;
    }
}
