package user.exported;

import user.PasswordHasher;

public class User {

    private int id;
    private String name;
    private String hashedPassword;

    public User(String name, String hashedPassword) {
        this(0, name, hashedPassword);
    }

    public User(int id, String name, String hashedPassword) {
        this.id = id;
        this.name = name;
        this.hashedPassword = hashedPassword;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean matchesPassword(String password) {
        return hashedPassword.equals(PasswordHasher.hash(password));
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
