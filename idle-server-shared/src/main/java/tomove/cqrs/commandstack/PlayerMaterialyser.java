package tomove.cqrs.commandstack;

import tomove.events.ItemReceivedEvent;
import tomove.events.JoeyEvent;
import tomove.events.PlayerCreatedEvent;

import java.util.List;

public class PlayerMaterialyser {

    private Player player;


    public Player materialyse(List<JoeyEvent> events) {
        for(JoeyEvent event : events){
            if(event instanceof PlayerCreatedEvent){
                handle((PlayerCreatedEvent) event);
            }
            if(event instanceof ItemReceivedEvent){
                handle((ItemReceivedEvent)event);
            }
        }
        return player;
    }

    private void handle(ItemReceivedEvent event) {
        if(player == null){
            throw new RuntimeException("Cannot handle an event for a player that hasnt been created");
        }
        player.addItem(event.getTick(), event.getItem(), event.getAmount());
    }

    private void handle(PlayerCreatedEvent event) {
        player = new Player(event.getTick(), event.getUsername());
    }
}
