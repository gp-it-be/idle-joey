package activity.privat.requirements;

import ids.SkillId;

public class WoodcuttingActivities {


    public static ActivityRequirements CHOP_TREES = build(1);
    public static ActivityRequirements CHOP_OAKS = build(15);
    public static ActivityRequirements CHOP_WILLOWS = build(30);
    public static ActivityRequirements CHOP_MAPLES = build(45);
    public static ActivityRequirements CHOP_YEWS = build(60);
    public static ActivityRequirements CHOP_MAGICS = build(80); //TODO levels checken met skillguide
    public static ActivityRequirements CHOP_REDWOODS = build(90);

    private static ActivityRequirements build(int minlevel) {
        return Activity.builder().
                withSkillReq(SkillId.WOODCUTTING, minlevel)
                .build();
    }


}
