package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerRequest;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import devs2blu.hackweek.eventmanager.entities.Speaker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SpeakerMapper extends AbstractMapper<Speaker, SpeakerRequest, SpeakerResponse> {
    public SpeakerMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Speaker> getEntityClass() {
        return Speaker.class;
    }

    @Override
    Class<SpeakerRequest> getRequestClass() {
        return SpeakerRequest.class;
    }

    @Override
    Class<SpeakerResponse> getResponseClass() {
        return SpeakerResponse.class;
    }
}
