package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerRequest;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import devs2blu.hackweek.eventmanager.entities.Speaker;
import devs2blu.hackweek.eventmanager.repositories.SpeakerRepository;
import devs2blu.hackweek.eventmanager.utils.UpdateEntities;
import devs2blu.hackweek.eventmanager.utils.mappers.SpeakerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeakerService {
    private final SpeakerRepository speakerRepository;
    private final SpeakerMapper mapper;

    public List<SpeakerResponse> findAll() {
        return mapper.toResponseList(speakerRepository.findAll());
    }

    public SpeakerResponse findById(Long id) {
        return speakerRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.SPEAKER_NOT_FOUND));
    }

    // public EventResponse findEventById(Long id) {
    //     var s = speakerRepository.findById(id).map(mapper::toResponse).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.SPEAKER_NOT_FOUND));

    //     return s.getEventId();
    // }

    public SpeakerResponse save(SpeakerRequest request) {
        return mapper.toResponse(
                speakerRepository.save(mapper.toEntity(request))
        );
    }

    public SpeakerResponse update(Long id, SpeakerRequest request) {
        Speaker speaker = speakerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.SPEAKER_NOT_FOUND));

        var updatedS = UpdateEntities.updateSpeaker(request, speaker);

        var savedS = this.speakerRepository.save(updatedS);

        return this.mapper.toResponse(savedS);
    }

    public void deleteById(Long id) {
        if (!speakerRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.SPEAKER_NOT_FOUND);
        }

        speakerRepository.deleteById(id);
    }
}
