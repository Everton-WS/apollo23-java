package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionRequest;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.entities.Question;
import devs2blu.hackweek.eventmanager.repositories.QuestionRepository;
import devs2blu.hackweek.eventmanager.utils.mappers.QuestionMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper mapper;

    public List<QuestionResponse> findAll() {
        return mapper.toResponseList(questionRepository.findAll());
    }

    public QuestionResponse findById(Long id) {
        return questionRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.QUESTION_NOT_FOUND));
    }

    public QuestionResponse save(QuestionRequest request) {
        return mapper.toResponse(
                questionRepository.save(mapper.toEntity(request))
        );
    }

    public QuestionResponse update(Long id, QuestionRequest request) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.QUESTION_NOT_FOUND));

        return mapper.toResponse(
                questionRepository.save(mapper.updateEntity(question, request))
        );
    }

    public void deleteById(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.QUESTION_NOT_FOUND);
        }

        questionRepository.deleteById(id);
    }
}

