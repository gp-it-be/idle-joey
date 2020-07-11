package requirement.privat.requirement;

public class RequirementSatisfactions {


    private Requirement requirement;
    private boolean satisfied;

    public RequirementSatisfactions(Requirement requirement, boolean satisfied) {
        this.requirement = requirement;
        this.satisfied = satisfied;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public boolean isSatisfied() {
        return satisfied;
    }
}
