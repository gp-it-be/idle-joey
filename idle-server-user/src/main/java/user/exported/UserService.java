package user.exported;

import requestresponses.CreateUserRequest;
import requestresponses.LoginRequest;
import requestresponses.LoginResponse;
import requestresponses.LogoutResponse;
import user.PasswordHasher;

import java.util.Optional;
import java.util.Random;

public class UserService {
    private final TokenToUsername tokenToUsername;
    private UserRepo userRepo;

    public UserService(UserRepo userRepo, TokenToUsername tokenToUsername) {

        this.userRepo = userRepo;
        this.tokenToUsername = tokenToUsername;
    }

    public Optional<User> createUser(CreateUserRequest request) {
        if(userRepo.findBy(request.getUsername()).isPresent()){
            return Optional.empty();
        }else{
            User user = userRepo.createUser(new User(request.getUsername(), PasswordHasher.hash(request.getPassWord())));
            return Optional.ofNullable(user);
        }
    }

    public LoginResponse loginUser(LoginRequest request) {
        return userRepo.findBy(request.getUsername())
                .map(foundUser -> foundUser.matchesPassword(request.getPassword()))
                .map(success -> {
                    if(success){
                    String token = generateToken();
                    tokenToUsername.sessionStarted(token, request.getUsername());
                    return LoginResponse.getSuccess(token);}else{
                        return LoginResponse.incorrectPassword();
                    }
                })
                .orElseGet(LoginResponse::userDoesntExist);
    }

    private String generateToken() {
        return new Random().nextInt() + "";
    }

    public LogoutResponse logoutCLient(String token) {
        tokenToUsername.sessionEnded(token);
        return LogoutResponse.success();
    }
}
