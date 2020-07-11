package requirement.exported;

import ids.ItemId;

public interface InventoryInfo {


    boolean hasItem(ItemId itemId);

    int amount(ItemId itemId);
}
