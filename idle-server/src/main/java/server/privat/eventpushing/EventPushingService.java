package server.privat.eventpushing;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import server.privat.ConnectedUsernames;

import java.util.concurrent.atomic.AtomicLong;

public class EventPushingService {

    private AtomicLong i = new AtomicLong();

    private ClientCommunications clientCommunications;

    public EventPushingService(ClientCommunications clientCommunications) {
        this.clientCommunications = clientCommunications;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendTest(){
        ((ConnectedUsernames) clientCommunications).getAllConnectedUsernames()
                .forEach(name -> sendEventToClientsOf(name, "randomeventFor"+name));
    }

    public void sendEventToClientsOf(String username, String event) {

        SseEmitter.SseEventBuilder sseEvent = SseEmitter.event()
                .data(event)
                .id(String.valueOf(i.incrementAndGet()))
                .name("?? wat hoort hier??");

        clientCommunications.emittersFor(username).forEach(
                sseEmitter -> {
                    try {
                        System.out.println("sending event to "+username);
                        sseEmitter.send(sseEvent);
                    } catch (Exception e) {
                        sseEmitter.completeWithError(e);
                    }
                }
        );
    }


    public void registerEmitter(String token, SseEmitter emitter) {
        clientCommunications.registerEmitterTo(token, emitter);
    }
}
