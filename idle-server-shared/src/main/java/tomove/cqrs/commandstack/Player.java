package tomove.cqrs.commandstack;

import ids.ItemId;
import lombok.Getter;
import ticking.JoeyClock;
import tomove.events.ItemReceivedEvent;
import tomove.events.JoeyEvent;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Player {
    private long lastUpatedTick;
    private String playerId;
    private Inventory inventory;

    public Player(long createdTick, String playerId) {
        this.lastUpatedTick = createdTick;
        this.playerId = playerId;
        inventory = new Inventory();
    }

    public List<JoeyEvent> progress(long ticksToProgress) {
        //In de MVP krijgtie gwn 1 log per tick.
        //Dit moet natuurlijk vervangen worden door het activity systeem.
        return Stream.of(
                new ItemReceivedEvent(JoeyClock.currentTick(), playerId, ticksToProgress, ItemId.NORMAL_LOGS)

        ).collect(Collectors.toList());
    }


//hmm beetje ambetant dat zowel de "maak events aub" en de "verwerkt events" method sin dezelfde klasse zitten, niet?
    public void addItem(long tick, ItemId item, long amount) {
        lastUpatedTick = tick;
        inventory.add(item, amount);
    }

    public Inventory inventoryTempExposedForHacky() {
        return inventory;
    }
}
