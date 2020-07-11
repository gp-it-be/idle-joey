package server.privat;

import user.exported.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class InMemorySessionManager implements SessionManager, SessionRetriever {


    private Map<String, String> sessions = new HashMap<>();

    @Override
    public void sessionStarted(String token, String username) {
        sessions.put(token, username);
    }

    @Override
    public String getUsernameFor(String token) {
        String result = sessions.get(token);
        if (result == null) {
            throw new RuntimeException("Invalid session token " + token);
        }
        return result;
    }
}
