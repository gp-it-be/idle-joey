package requirement.privat.requirement;

import requirement.exported.PlayerInfo;
import ids.SkillId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Activity implements ActivityRequirements {

    private List<StatRequirement> statReqs;


    private Activity(List<StatRequirement> statReqs) {
        this.statReqs = statReqs;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public List<RequirementSatisfactions> startRequirementsInfo(PlayerInfo playerInfo) {
        return statReqs.stream()
                .map(statRe -> new RequirementSatisfactions(statRe, statRe.satisfied(playerInfo.stats())))
                .collect(Collectors.toList());
    }

    @Override
    public boolean canStart(PlayerInfo playerInfo) {
        return statReqs.stream().allMatch(req -> req.satisfied(playerInfo.stats()));
    }

    @Override
    public boolean canContinue(PlayerInfo playerInfo) {
        return true;
    }

    public static class Builder {

        private List<StatRequirement> statReqs = new ArrayList<>();


        public Builder withSkillReq(SkillId woodcutting, int minlevel) {
            statReqs.add(StatRequirement.create(woodcutting, minlevel));
            return this;
        }

        public Activity build() {
            return new Activity(statReqs);
        }
    }
}
