package server.privat.eventpushing;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface ClientCommunications {
    void registerEmitterTo(String token, SseEmitter emitter);

    List<SseEmitter> emittersFor(String username);
}
