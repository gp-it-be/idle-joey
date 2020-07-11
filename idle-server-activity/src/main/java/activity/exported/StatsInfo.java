package activity.exported;

import ids.SkillId;

public interface StatsInfo {


    boolean hasLevel(SkillId skill, int level);

    int level(SkillId skillId);

}
