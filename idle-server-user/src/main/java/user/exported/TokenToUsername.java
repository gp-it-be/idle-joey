package user.exported;

public interface TokenToUsername {


    void sessionStarted(String token, String username);


    void sessionEnded(String token);
}
