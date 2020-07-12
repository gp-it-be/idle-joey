package server.privat;
import org.springframework.web.bind.annotation.*;
import requestresponses.*;
import requirement.exported.ActivityController;
import user.exported.UserController;

@RestController()
public class RestOperationsController {

    private UserController userController;
    private ActivityController activityController;

    public RestOperationsController(UserController userController, ActivityController activityController) {
        this.userController = userController;
        this.activityController = activityController;
    }


    //TODO nog de argumenten valideren voor deftige errormessages

    @PostMapping("createuser")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request){
        return userController.createUser(request);
    }



    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return userController.loginUser(request);
    }



    @PostMapping("startactivity")
    public StartActivityResponse startActivity(@RequestHeader("token") String token, @RequestBody StartActivityRequest request){
        return activityController.startActivity(token, request);
    }

}
