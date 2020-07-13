package tomove.cqrs.commandstack;

import ids.ItemId;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<ItemId, Long> items = new HashMap<>();


    public void add(ItemId item, long amount) {
        items.compute(item, (key, count)->count==null? amount : count+amount );
    }
}
