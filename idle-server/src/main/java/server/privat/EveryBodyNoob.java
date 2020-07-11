package server.privat;

import activity.exported.InventoryInfo;
import activity.exported.PlayerInfo;
import activity.exported.PlayerInfoProvider;
import activity.exported.StatsInfo;
import ids.ItemId;
import ids.SkillId;

public class EveryBodyNoob implements PlayerInfoProvider {
    @Override
    public PlayerInfo getPlayerInfo(String username) {
        return new PlayerInfo() {
            @Override
            public InventoryInfo inventory() {
                return new InventoryInfo() {
                    @Override
                    public boolean hasItem(ItemId itemId) {
                        return false;
                    }

                    @Override
                    public int amount(ItemId itemId) {
                        return 0;
                    }
                };
            }

            @Override
            public StatsInfo stats() {
                return new StatsInfo() {
                    @Override
                    public boolean hasLevel(SkillId skill, int level) {
                        return level <= 1;
                    }

                    @Override
                    public int level(SkillId skillId) {
                        return 1;
                    }
                };
            }
        };
    }
}
