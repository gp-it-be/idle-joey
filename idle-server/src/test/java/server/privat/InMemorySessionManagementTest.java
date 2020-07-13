package server.privat;

import org.junit.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class InMemorySessionManagementTest {


    private InMemorySessionManagement manager = new InMemorySessionManagement();

    @Test
    public void twoConnectionsIsOneUsername() {
        manager.sessionStarted("token1", "joske");
        manager.sessionStarted("token2", "joske");

        assertEquals(1, manager.getAllConnectedUsernames().size());
    }



    @Test
    public void connectedUsernameIsReturned() {
        manager.sessionStarted("token1", "joske");
        assertTrue(manager.getAllConnectedUsernames().contains("joske"));
    }

    @Test
    public void notConnectedUsernameIsNotReturned() {
        assertTrue(manager.getAllConnectedUsernames().isEmpty());
    }


    @Test
    public void diconnectedUsernameIsNotReturned() {
        manager.sessionStarted("token1", "joske");
        manager.sessionEnded("token1");
        assertTrue(manager.getAllConnectedUsernames().isEmpty());
    }


    @Test
    public void twoConnectionsIsTwoEmitters() {
        manager.sessionStarted("token1", "joske");
        manager.sessionStarted("token2", "joske");

        manager.registerEmitterTo("token1", mock(SseEmitter.class));
        manager.registerEmitterTo("token2", mock(SseEmitter.class));

        assertEquals(2, manager.emittersFor("joske").size());
    }

    @Test
    public void disconnectingOneClientKeepsOthers() {
        manager.sessionStarted("token1", "joske");
        manager.sessionStarted("token2", "joske");

        manager.registerEmitterTo("token1", mock(SseEmitter.class));
        manager.registerEmitterTo("token2", mock(SseEmitter.class));

        manager.sessionEnded("token1");
        assertTrue(manager.getAllConnectedUsernames().contains("joske"));
        assertEquals(1, manager.emittersFor("joske").size());
    }

}