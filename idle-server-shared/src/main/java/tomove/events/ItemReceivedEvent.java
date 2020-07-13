package tomove.events;

import ids.ItemId;
import lombok.Value;

@Value
public class ItemReceivedEvent implements JoeyEvent {

    private long tick;
    private String username;
    private long amount;
    private ItemId item;
}
