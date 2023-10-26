package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.myTreasure.MyTreasureRequest;
import devs2blu.hackweek.eventmanager.dtos.myTreasure.MyTreasureResponse;
import devs2blu.hackweek.eventmanager.entities.MyTreasure;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MyTreasureMapper extends AbstractMapper<MyTreasure, MyTreasureRequest, MyTreasureResponse> {
    public MyTreasureMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<MyTreasure> getEntityClass() {
        return MyTreasure.class;
    }

    @Override
    Class<MyTreasureRequest> getRequestClass() {
        return MyTreasureRequest.class;
    }

    @Override
    Class<MyTreasureResponse> getResponseClass() {
        return MyTreasureResponse.class;
    }
}
