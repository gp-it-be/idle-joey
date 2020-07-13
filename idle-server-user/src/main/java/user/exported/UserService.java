package user.exported;

import requestresponses.CreateUserRequest;
import requestresponses.LoginRequest;
import requestresponses.LoginResponse;
import requestresponses.LogoutResponse;
import ticking.JoeyClock;
import tomove.cqrs.commandstack.CreatePlayerCommand;
import tomove.cqrs.commandstack.PlayerAggregator;
import user.PasswordHasher;

import java.util.Optional;
import java.util.Random;

public class UserService {
    private final TokenToUsername tokenToUsername;
    private UserRepo userRepo;
private PlayerAggregator playerAggregator;
    public UserService(UserRepo userRepo, TokenToUsername tokenToUsername, PlayerAggregator playerAggregator) {

        this.userRepo = userRepo;
        this.tokenToUsername = tokenToUsername;
        this.playerAggregator = playerAggregator;
    }

    public Optional<User> createUser(CreateUserRequest request) {
        if (userRepo.findBy(request.getUsername()).isPresent()) {
            return Optional.empty();
        } else {
            User user = userRepo.createUser(new User(request.getUsername(), PasswordHasher.hash(request.getPassWord())));
            playerAggregator.handleCreateCommand(
                    new CreatePlayerCommand(request.getUsername(), JoeyClock.currentTick())
            );
            return Optional.ofNullable(user);
        }
    }

    public LoginResponse loginUser(LoginRequest request) {
        return userRepo.findBy(request.getUsername())
                .map(foundUser -> foundUser.matchesPassword(request.getPassword()))
                .map(success -> {
                    if (success) {
                        String token = generateToken();
                        tokenToUsername.sessionStarted(token, request.getUsername());
                        return LoginResponse.getSuccess(token);
                    } else {
                        return LoginResponse.incorrectPassword();
                    }
                })
                .orElseGet(LoginResponse::userDoesntExist);
    }

    private String generateToken() {
        return new Random().nextInt() + "";
    }

    public LogoutResponse logoutClient(String token) {
        tokenToUsername.sessionEnded(token);
        return LogoutResponse.success();
    }
}
