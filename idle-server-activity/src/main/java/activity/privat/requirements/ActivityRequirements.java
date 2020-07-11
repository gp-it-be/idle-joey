package activity.privat.requirements;

import activity.exported.PlayerInfo;

import java.util.List;

public interface ActivityRequirements {

    List<RequirementSatisfactions> startRequirementsInfo(PlayerInfo playerInfo);

    boolean canStart(PlayerInfo playerInfo);

    boolean canContinue(PlayerInfo playerInfo);

}
