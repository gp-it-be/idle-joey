package server.user;

public class PasswordHasher {


    public static String hash(String password) {
        return
                (password.hashCode() + "32").hashCode() + "";
    }

}
