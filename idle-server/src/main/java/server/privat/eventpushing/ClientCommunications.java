package server.privat.eventpushing;

import java.util.List;

public interface ClientCommunications {
    void registerEmitterTo(String token, ClientEventEmitter emitter);

    List<ClientEventEmitter> emittersFor(String username);


    ClientEventEmitter singleEmitterFor(String token);

}
