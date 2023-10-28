package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.question.QuestionRequest;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.entities.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper extends AbstractMapper<Question, QuestionRequest, QuestionResponse> {
    public QuestionMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Question> getEntityClass() {
        return Question.class;
    }

    @Override
    Class<QuestionRequest> getRequestClass() {
        return QuestionRequest.class;
    }

    @Override
    Class<QuestionResponse> getResponseClass() {
        return QuestionResponse.class;
    }
}
