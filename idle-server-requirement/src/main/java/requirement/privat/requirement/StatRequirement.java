package requirement.privat.requirement;

import requirement.exported.StatsInfo;
import ids.SkillId;

public class StatRequirement implements Requirement<StatsInfo>{


    private int level;
    private SkillId skill;


    public static StatRequirement create(SkillId skill, int level){
        return new StatRequirement(level, skill);
    }

    private StatRequirement(int level, SkillId skill) {
        this.level = level;
        this.skill = skill;
    }

    @Override
    public boolean satisfied(StatsInfo statInfo) {
        return statInfo.level(skill) >= level;
    }

    @Override
    public String description() {
        return "You need " + level + " in " + skill.name();
    }
}
