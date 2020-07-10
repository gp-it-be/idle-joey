package server.user;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepo {

    Stream<User> allUsers();

    User createUser(User user);

    Optional<User> findBy(String username);
}
