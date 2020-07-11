package requestresponses;

public class StartActivityRequest {

    private String activityName;

    public StartActivityRequest(String activityName) {
        this.activityName = activityName;
    }


    public String getActivityName() {
        return activityName;
    }
}
