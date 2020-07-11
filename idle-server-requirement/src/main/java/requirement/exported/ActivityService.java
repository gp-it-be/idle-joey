package requirement.exported;

import ids.ActivityId;
import requirement.privat.requirement.ActivityRequirements;
import requirement.privat.requirement.Requirement;
import requirement.privat.requirement.RequirementSatisfactions;
import requirement.privat.requirement.WoodcuttingActivities;
import requestresponses.StartActivityResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//Deze module moet volgens mij vermijden van zelf ook de activities te laten ticket etc
//dit zou als het ware 100% moeten gebruikt kunnen worden in een WIKI style app
public class ActivityService {

    private PlayerInfoProvider playerInfoProvider;

    //moven?
    private static Map<ActivityId, ActivityRequirements> requirementMap;

    static {
        requirementMap = new HashMap<>();
        requirementMap.put(ActivityId.CHOP_TREES, WoodcuttingActivities.CHOP_TREES);
        requirementMap.put(ActivityId.CHOP_OAKS, WoodcuttingActivities.CHOP_OAKS);
    }

    public ActivityService(PlayerInfoProvider playerInfoProvider) {
        this.playerInfoProvider = playerInfoProvider;
    }

    public StartActivityResponse startActivity(String username, ActivityId activityId) {
        ActivityRequirements requirements = requirementMap.get(activityId);

        PlayerInfo playerInfo = playerInfoProvider.getPlayerInfo(username);

        if (requirements.canStart(playerInfo)) {
            //tell some other object the activity is started
            //EVENT? dan ook event voor login logout
            return StartActivityResponse.started(activityId.name());
        } else {
            return StartActivityResponse.cantStart(toReason(requirements.startRequirementsInfo(playerInfo)));
        }


    }

    private String toReason(List<RequirementSatisfactions> requirementSatisfactions) {
        return "Following requirements not satisfied: " + requirementSatisfactions.stream()
                .filter(a -> !a.isSatisfied())
                .map(RequirementSatisfactions::getRequirement)
                .map(Requirement::description)
                .collect(Collectors.joining(","));
    }
}
