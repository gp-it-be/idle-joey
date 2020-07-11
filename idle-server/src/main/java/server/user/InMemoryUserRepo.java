package server.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class InMemoryUserRepo implements UserRepo {

    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Stream<User> allUsers() {
        return users.stream();
    }

    @Override
    public User createUser(User user) {
        if (user.getId() != 0) {
            throw new RuntimeException("User to be created should not already have an ID " + user.getId());
        }

        User created = new User(nextId++, user.getName(), user.getHashedPassword());

        users.add(created);
        return created;

    }

    @Override
    public Optional<User> findBy(String username) {
        return allUsers().filter(user -> user.getName().equals(username)).findFirst();
    }
}
