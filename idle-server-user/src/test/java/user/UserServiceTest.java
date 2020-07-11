package user;

import org.junit.Before;
import org.junit.Test;
import requestresponses.CreateUserRequest;
import requestresponses.LoginRequest;
import requestresponses.LoginResponse;
import user.exported.SessionManager;
import user.exported.User;
import user.exported.UserRepo;
import user.exported.UserService;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserRepo userRepo;
    private UserService userService;

    @Before
    public void setup() {
        userRepo = new UserRepoStub();
        SessionManagerStub sessionManagerStub = new SessionManagerStub();
        userService = new UserService(userRepo, sessionManagerStub);
    }

    @Test
    public void createUser_generatesId() {
        String requestedName = "cowkilled37";
        CreateUserRequest request = new CreateUserRequest(requestedName, "joske7");

        Optional<User> userOpt = userService.createUser(request);

        userOpt.ifPresentOrElse(user -> {
            assertTrue(user.getId() > 0);
            assertEquals(requestedName, user.getName());
        }, () -> fail("user should be present"));
    }


    @Test
    public void createUser_existingUsername_doesntCreate() {

        userRepo.createUser(new User("cowkilled37", "fillemon3"));

        CreateUserRequest request = new CreateUserRequest("cowkilled37", "joske7");
        Optional<User> userOpt = userService.createUser(request);

        assertFalse(userOpt.isPresent());
    }


    @Test
    public void loginWithCorrectPassword(){
        String requestedName = "cowkilled37";
        String requestedPassword = "iluvcows";
        CreateUserRequest request = new CreateUserRequest(requestedName, requestedPassword);
        userService.createUser(request);

        LoginResponse loginResponse = userService.loginUser(new LoginRequest(requestedName, requestedPassword));

        assertTrue(loginResponse.success());


    }



    @Test
    public void loginWithWrongPassword(){
        String requestedName = "cowkilled37";
        String requestedPassword = "iluvcows";
        CreateUserRequest request = new CreateUserRequest(requestedName, requestedPassword);
        userService.createUser(request);

        LoginResponse loginResponse = userService.loginUser(new LoginRequest(requestedName, "wrongpassword"));

        assertFalse(loginResponse.success());
    }


    @Test
    public void loginNotExistingUser(){
        LoginResponse loginResponse = userService.loginUser(new LoginRequest("cowkilled37", "franky"));
        assertFalse(loginResponse.success());
    }

}