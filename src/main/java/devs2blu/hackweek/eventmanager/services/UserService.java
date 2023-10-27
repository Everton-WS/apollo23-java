package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.entities.User;
import devs2blu.hackweek.eventmanager.repositories.EventRepository;
import devs2blu.hackweek.eventmanager.repositories.UserRepository;
import devs2blu.hackweek.eventmanager.utils.mappers.EventMapper;
import devs2blu.hackweek.eventmanager.utils.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final UserMapper userMapper;
    private final EventMapper eventMapper;

    public List<UserResponse> getAllUsersWithEvents() {
        return userMapper.toResponseList(userRepository.findAllWithEvents());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND));

        UserResponse userResponse = userMapper.toResponse(user);

        List<Event> userEvents = eventRepository.findByUsers_Id(id);
        userResponse.setEvents(eventMapper.toResponseList(userEvents));

        return userResponse;
    }

}
