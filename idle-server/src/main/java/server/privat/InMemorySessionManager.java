package server.privat;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import server.privat.eventpushing.ClientHolder;
import user.exported.SessionManager;

import java.util.*;

public class InMemorySessionManager implements SessionManager, SessionRetriever, ClientHolder {


    private Map<String, String> sessions = new HashMap<>();
    private Map<String, List<SseEmitter>> emittersForUsername = new HashMap<>();

    @Override
    public void sessionStarted(String token, String username) {
        sessions.put(token, username);
    }


    @Override
    public void registerEmitterTo(String token, SseEmitter emitter) {
        String username = getUsernameFor(token);
        emittersForUsername.computeIfAbsent(username, key -> new ArrayList<>())
                .add(emitter);
    }

    @Override
    public List<SseEmitter> emittersFor(String username) {
        return emittersForUsername.computeIfAbsent(username, key -> new ArrayList<>());
    }


    @Override
    public String getUsernameFor(String token) {
        String userName = sessions.get(token);
        if (userName == null) {
            throw new RuntimeException("Invalid session token " + token);
        }
        return userName;
    }

    @Override
    public Set<String> getAllConnectedUsernames() {
        return new HashSet<>(sessions.values());
    }

}
