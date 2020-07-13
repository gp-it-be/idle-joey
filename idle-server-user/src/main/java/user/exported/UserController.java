package user.exported;

import requestresponses.*;

import java.util.Optional;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
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

    public LogoutResponse logoutClient(String token) {
        return userService.logoutClient(token);
    }
}
