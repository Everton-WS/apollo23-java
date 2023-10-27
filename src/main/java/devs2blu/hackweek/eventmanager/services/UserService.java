package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserRequest;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.entities.Treasure;
import devs2blu.hackweek.eventmanager.entities.User;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.QuestionRepository;
import devs2blu.hackweek.eventmanager.repositories.UserRepository;
import devs2blu.hackweek.eventmanager.utils.mappers.ActivityMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.EventMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.QuestionMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.TreasureMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final UserMapper userMapper;
    private final EventMapper eventMapper;
    private final TreasureMapper treasureMapper;
    private final QuestionMapper questionMapper;
    private final ActivityMapper activityMapper;

    public List<UserResponse> getAllUsersWithEvents() {
        return userMapper.toResponseList(userRepository.findAllWithEvents());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));

        UserResponse userResponse = userMapper.toResponse(user);

        return userResponse;
    }

    public List<EventResponse> getUserEvents(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));  
                
        return eventMapper.toResponseList(user.getEvents());
    }

    public List<TreasureResponse> getUserTreasures(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));  

        return treasureMapper.toResponseList(user.getTreasures());
    }

        public List<ActivityResponse> getUserActivities(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));  

        return activityMapper.toResponseList(user.getActivities());
    }

    public List<QuestionResponse> getUserQuestions(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        var questions = this.questionRepository.findAll().stream().filter((q) -> {
            return q.getUser().getId() == id;
        }).toList();

        return questionMapper.toResponseList(questions);
    }

    public UserResponse createUser(UserRequest uRequest) {
        var createdUser = userMapper.toEntity(uRequest);

        var newUser = this.userRepository.save(createdUser);

        return userMapper.toResponse(newUser);
    }

    public Integer getUserScore(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));  

        Integer score = user.getTreasures().stream()
            .map(Treasure::getScore)
            .reduce(0, Integer::sum);

        return score;
    }

    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));

        this.userRepository.delete(user);

        return userMapper.toResponse(user);
    }

    // TODO -> getUserByEmail

}
