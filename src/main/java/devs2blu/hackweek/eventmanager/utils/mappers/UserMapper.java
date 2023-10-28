package devs2blu.hackweek.eventmanager.utils.mappers;

import devs2blu.hackweek.eventmanager.dtos.user.UserRequest;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserRequest, UserResponse> {
    public UserMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    Class<UserRequest> getRequestClass() {
        return UserRequest.class;
    }

    @Override
    Class<UserResponse> getResponseClass() {
        return UserResponse.class;
    }
}
