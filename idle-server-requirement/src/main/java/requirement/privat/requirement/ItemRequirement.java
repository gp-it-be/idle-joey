package requirement.privat.requirement;

import requirement.exported.InventoryInfo;
import ids.ItemId;

public class ItemRequirement implements Requirement<InventoryInfo>{


    private int amount;
    private ItemId item;

    public static ItemRequirement oneOf(ItemId item){
        return new ItemRequirement(1, item);
    }

    public static ItemRequirement multiple(ItemId item, int amount){
        return new ItemRequirement(amount, item);
    }

    private ItemRequirement(int amount, ItemId item) {
        this.amount = amount;
        this.item = item;
    }

    @Override
    public boolean satisfied(InventoryInfo inventory) {
        return inventory.amount(item) >= amount;
    }

    @Override
    public String description() {
        return "You need " + amount + " " + item.name();
    }
}
