package tomove.cqrs.commandstack;

import tomove.events.EventStore;
import tomove.events.JoeyEvent;

import java.util.List;

public class PlayerUtility {
    public static Player restoreState(String playerId, EventStore eventStore) {
        List<JoeyEvent> eventForPlayer = eventStore.getEventsFor(playerId);
        PlayerMaterialyser playerWrapper = new PlayerMaterialyser();
        return playerWrapper.materialyse(eventForPlayer);
    }
}
