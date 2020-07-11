package server.user.tempexported;

import server.user.User;
import server.user.UserService;
import server.user.exported.CreateUserRequest;
import server.user.exported.CreateUserResponse;
import server.user.exported.LoginRequest;
import server.user.exported.LoginResponse;

import java.util.Optional;

public class Controller {

    private UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<User> created = userService.createUser(request);
        return created.map(user -> CreateUserResponse.created(user.getName(), user.getId() + ""))
                .orElseGet(CreateUserResponse::notCreated);
    }


    public LoginResponse loginUser(LoginRequest request) {
        return userService.loginUser(request);
    }
}
