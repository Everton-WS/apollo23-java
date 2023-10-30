package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionRequest;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.entities.Question;
import devs2blu.hackweek.eventmanager.repositories.QuestionRepository;
import devs2blu.hackweek.eventmanager.utils.UpdateEntities;
import devs2blu.hackweek.eventmanager.utils.mappers.QuestionMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionResponse> findAll() {
        return questionMapper.toResponseList(questionRepository.findAll());
    }

    public QuestionResponse findById(Long id) {
        return questionRepository.findById(id)
                .map(questionMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.QUESTION_NOT_FOUND));
    }

    public QuestionResponse save(QuestionRequest request) {
        return questionMapper.toResponse(
                questionRepository.save(questionMapper.toEntity(request))
        );
    }

    public QuestionResponse update(Long id, QuestionRequest request) {
        Question q = questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.QUESTION_NOT_FOUND));

        var updateQ = UpdateEntities.updateQuestion(request, q);

        var savedQ = this.questionRepository.save(updateQ);

        return this.questionMapper.toResponse(savedQ);
    }

    public void deleteById(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.QUESTION_NOT_FOUND);
        }

        questionRepository.deleteById(id);
    }
}

