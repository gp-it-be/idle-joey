package server.privat;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import server.privat.eventpushing.ClientCommunications;
import user.exported.TokenToUsername;

import java.util.*;
import java.util.stream.Collectors;

public class InMemorySessionManagement implements TokenToUsername, ConnectedUsernames, ClientCommunications {


    private Map<String, String> tokenToUserNameSessions = new HashMap<>();

    private Map<String, SseEmitter> emittersForToken = new HashMap<>();


    @Override
    public void sessionStarted(String token, String username) {
        tokenToUserNameSessions.put(token, username);
    }

    @Override
    public void sessionEnded(String token) {
        String removedUserName = tokenToUserNameSessions.remove(token);
        emittersForToken.remove(token);
    }


    @Override
    public void registerEmitterTo(String token, SseEmitter emitter) {
        if (!tokenToUserNameSessions.containsKey(token)) {
            throw new RuntimeException("Should register token session before registering eventemitter " + token);
        }
        emittersForToken.put(token, emitter);
    }

    @Override
    public List<SseEmitter> emittersFor(String username) {
        Set<String> tokensOfUsername = tokenToUserNameSessions
                .entrySet()
                .stream()
                .filter(entry -> username.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return tokensOfUsername
                .stream()
                .map(token -> emittersForToken.get(token))
                .collect(Collectors.toList());
    }


    @Override
    public String getUsernameFor(String token) {
        String userName = tokenToUserNameSessions.get(token);
        if (userName == null) {
            throw new RuntimeException("Invalid session token " + token);
        }
        return userName;
    }

    @Override
    public Set<String> getAllConnectedUsernames() {
        return new HashSet<>(tokenToUserNameSessions.values());
    }

}
