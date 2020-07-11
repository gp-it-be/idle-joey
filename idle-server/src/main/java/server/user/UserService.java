package server.user;

import server.user.exported.CreateUserRequest;
import server.user.exported.LoginRequest;
import server.user.exported.LoginResponse;

import java.util.Optional;

public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {

        this.userRepo = userRepo;
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
                .map(LoginResponse::new)
                .orElseGet(LoginResponse::userDoesntExist);
    }
}
