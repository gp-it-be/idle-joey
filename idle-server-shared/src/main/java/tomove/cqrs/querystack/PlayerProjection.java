package tomove.cqrs.querystack;

import tomove.cqrs.commandstack.Inventory;
import tomove.cqrs.commandstack.Player;
import tomove.cqrs.commandstack.PlayerUtility;
import tomove.events.EventStore;

public class PlayerProjection {

    //TODO de projectie dient als datasource de eventstore NIET te gebruiken. vervangen door een playerReadRepo
    //De playerReadRepo wordt geupdated door de Projector die ook de events binnenkrijgt en verwerkt!
    private EventStore eventStore;


    public Inventory getInventory(String playerId){
        //dit moet dan uiteraard ook de readrepo gebruiken
        Player player = PlayerUtility.restoreState(playerId, eventStore);
        return player.inventoryTempExposedForHacky();
    }



}
