package server.privat.events;


import server.privat.eventpushing.ClientCommunications;
import tomove.events.EventStore;
import tomove.events.JoeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientUpdatingInMemoryEventStore implements EventStore {

    Map<String, List<JoeyEvent>> dataPerUser = new HashMap<>();

    private ClientCommunications clientCommunications;

    public ClientUpdatingInMemoryEventStore(ClientCommunications clientCommunications) {
        this.clientCommunications = clientCommunications;
    }

    @Override
    public void storeEvent(JoeyEvent event) {
        dataPerUser
                .computeIfAbsent(event.getUsername(), key -> new ArrayList<>())
                .add(event);
        clientCommunications.emittersFor(event.getUsername())
                .forEach(emitter->emitter.sendData(event.toString()));
    }

    @Override
    public List<JoeyEvent> getEventsFor(String username) {
        return dataPerUser
                .computeIfAbsent(username, key -> new ArrayList<>());
    }
}
