package server.tempexported;

import requirement.exported.ActivityController;
import requestresponses.*;
import server.privat.SessionRetriever;
import user.exported.*;

public class Client {

    private UserController userController;
    private ActivityController activityController;
    private SessionRetriever sessionRetriever;

    public Client(UserController userController, ActivityController activityController, SessionRetriever sessionRetriever) {
        this.userController = userController;
        this.activityController = activityController;
        this.sessionRetriever = sessionRetriever;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        return  userController.createUser(request);
    }


    public LoginResponse loginUser(LoginRequest request) {
        return userController.loginUser(request);
    }

    public StartActivityResponse startActivity(String token, String activityName){
        String username = sessionRetriever.getUsernameFor(token);
        return activityController.startActivity(username, new StartActivityRequest(activityName));
    }
}
