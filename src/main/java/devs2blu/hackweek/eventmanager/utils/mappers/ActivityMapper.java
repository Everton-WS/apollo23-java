package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper extends AbstractMapper<Activity, ActivityRequest, ActivityResponse> {
    public ActivityMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Activity> getEntityClass() {
        return Activity.class;
    }

    @Override
    Class<ActivityRequest> getRequestClass() {
        return ActivityRequest.class;
    }

    @Override
    Class<ActivityResponse> getResponseClass() {
        return ActivityResponse.class;
    }
}
