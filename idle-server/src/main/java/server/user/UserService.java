package server.user;

import server.user.exported.CreateUserRequest;

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
            User user = userRepo.createUser(new User(request.getUsername()));
            return Optional.ofNullable(user);
        }
    }
}
