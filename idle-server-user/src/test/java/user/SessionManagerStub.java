package user;

import user.exported.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class SessionManagerStub implements SessionManager {


    private Map<String, String> sessions = new HashMap<>();

    @Override
    public void sessionStarted(String token, String username) {
        sessions.put(token, username);
    }

}
