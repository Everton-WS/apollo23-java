package devs2blu.hackweek.eventmanager.services;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.login.LoginRequest;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.User;
import devs2blu.hackweek.eventmanager.repositories.UserRepository;
import devs2blu.hackweek.eventmanager.utils.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse login(LoginRequest loginRequest){
        Optional<User> user = userRepository.findByEmail(loginRequest.email());
        if (user.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }
        return userMapper.toResponse(user.get());
    }
}
