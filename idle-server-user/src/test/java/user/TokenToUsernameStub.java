package user;

import user.exported.TokenToUsername;

import java.util.HashMap;
import java.util.Map;

public class TokenToUsernameStub implements TokenToUsername {


    private Map<String, String> sessions = new HashMap<>();

    @Override
    public void sessionStarted(String token, String username) {
        sessions.put(token, username);
    }

    @Override
    public void sessionEnded(String token) {
        sessions.remove(token);
    }

}
