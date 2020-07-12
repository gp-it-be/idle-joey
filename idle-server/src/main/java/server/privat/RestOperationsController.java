package server.privat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import requestresponses.*;
import server.tempexported.Client;

@RestController()
public class RestOperationsController {

    private Client client;

    public RestOperationsController(Client client) {
        this.client = client;
    }

//TODO nog de argumenten valideren voor deftige errormessages

    @PostMapping("createuser")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request){
        return client.createUser(request);
    }



    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return client.loginUser(request);
    }



    @PostMapping("startactivity")
    public StartActivityResponse startActivity(@RequestHeader("token") String token, @RequestBody StartActivityRequest request){
        return client.startActivity(token, request.getActivityName());
    }
}
