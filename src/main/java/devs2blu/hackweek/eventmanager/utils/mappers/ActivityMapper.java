package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import devs2blu.hackweek.eventmanager.entities.Activity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper extends AbstractMapper<Activity, ActivityRequest, ActivityResponse> {
    @Autowired
    private SpeakerMapper speakerMapper;
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

    public ActivityResponse toResponseWithSpeaker(Activity activity) {
        ActivityResponse response = toResponse(activity);
        SpeakerResponse speakerResponse = speakerMapper.toResponse(activity.getSpeaker());
        response.setSpeakerResponse(speakerResponse);
        return response;
    }
}
