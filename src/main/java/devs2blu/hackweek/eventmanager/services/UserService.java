package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.dtos.user.UserRequest;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.Treasure;
import devs2blu.hackweek.eventmanager.entities.User;
import devs2blu.hackweek.eventmanager.repositories.QuestionRepository;
import devs2blu.hackweek.eventmanager.repositories.UserRepository;
import devs2blu.hackweek.eventmanager.utils.UpdateEntities;
import devs2blu.hackweek.eventmanager.utils.mappers.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserResponse> findAllUsersWithEvents() {
        List<User> users = userRepository.findAllWithEvents();
        return users.stream()
                .map(user -> {
                    UserResponse userResponse = userMapper.toResponse(user);
                    List<EventResponse> eventResponses = user.getEvents().stream()
                            .map(eventMapper::toResponse)
                            .toList();
                    userResponse.setEventResponses(eventResponses);
                    return userResponse;
                })
                .toList();
    }


    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));

        UserResponse userResponse = userMapper.toResponse(user);
        List<EventResponse> eventResponses = user.getEvents().stream()
                .map(eventMapper::toResponse)
                .toList();
        userResponse.setEventResponses(eventResponses);

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

    public UserResponse updateUser(Long id, UserRequest uRequest) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));  

        var updatedU = UpdateEntities.updateUser(uRequest, user);

        var savedU = this.userRepository.save(updatedU);

        return this.userMapper.toResponse(savedU);
    }

    public Integer getUserScore(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));

        return user.getTreasures().stream()
            .map(Treasure::getScore)
            .reduce(0, Integer::sum);
    }

    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));

        this.userRepository.delete(user);

        return userMapper.toResponse(user);
    }

    public UserResponse getUserByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));
        return userMapper.toResponse(user);
    }
}
