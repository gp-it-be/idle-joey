package tomove.cqrs.commandstack;

import ticking.JoeyClock;
import tomove.events.EventStore;
import tomove.events.JoeyEvent;
import tomove.events.PlayerCreatedEvent;

import java.util.List;

//één instantie is voor alle players
public class PlayerAggregator {

    private EventStore eventStore;

    public PlayerAggregator(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void handleCreateCommand(CreatePlayerCommand command) {
        //TODO checken of de user met id al bestaat
        //hoe dit best doen
        //PlayerUtility.playerexists(eventstore) ?
        eventStore.storeEvent(new PlayerCreatedEvent(command.getTick(), command.getId()));
    }


    //is een RandomSeedSetEvent de oplossing voor repeated randomness? It might just be ...


    public void handleGoToTickCommand(GoToTickCommand command) {
        //TODO later opspliten in een aggregate voor Player en één voor Activity ? maakt da sense?
        Player player = PlayerUtility.restoreState(command.getPlayerId(), eventStore);
        long lastUpatedTick = player.getLastUpatedTick();
        long ticksToProgress = JoeyClock.ticksPassedSince(lastUpatedTick);
        List<JoeyEvent> events = player.progress(ticksToProgress);
        events.forEach(eventStore::storeEvent);
    }


}