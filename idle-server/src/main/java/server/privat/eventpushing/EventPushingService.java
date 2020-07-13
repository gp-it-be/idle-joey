package server.privat.eventpushing;

import org.springframework.scheduling.annotation.Scheduled;
import server.privat.ConnectedUsernames;

public class EventPushingService {


    private ClientCommunications clientCommunications;

    public EventPushingService(ClientCommunications clientCommunications) {
        this.clientCommunications = clientCommunications;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendTest() {
        ((ConnectedUsernames) clientCommunications).getAllConnectedUsernames()
                .forEach(name -> sendEventToClientsOf(name, "randomeventFor" + name));
    }

    public void sendEventToClientsOf(String username, String event) {


        clientCommunications.emittersFor(username).forEach(
                sseEmitter -> {

                    System.out.println("sending event to " + username);
                    sseEmitter.sendData(event);

                }
        );
    }


    public void registerEmitter(String token, ClientEventEmitter emitter) {
        clientCommunications.registerEmitterTo(token, emitter);
    }
}
