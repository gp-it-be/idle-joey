package server.user;

import org.junit.Before;
import org.junit.Test;
import server.user.exported.CreateUserRequest;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserRepo userRepo;
    private UserService userService;

    @Before
    public void setup() {
        userRepo = new InMemoryUserRepo();
        userService = new UserService(userRepo);
    }

    @Test
    public void createUser_generatesId() {
        String requestedName = "cowkilled37";
        CreateUserRequest request = new CreateUserRequest(requestedName);

        Optional<User> userOpt = userService.createUser(request);

        userOpt.ifPresentOrElse(user -> {
            assertTrue(user.getId() > 0);
            assertEquals(requestedName, user.getName());
        }, () -> fail("user should be present"));
    }


    @Test
    public void createUser_existingUsername_doesntCreate() {

        userRepo.createUser(new User("cowkilled37"));

        CreateUserRequest request = new CreateUserRequest("cowkilled37");
        Optional<User> userOpt = userService.createUser(request);

        assertFalse(userOpt.isPresent());
    }




}