package requestresponses;

public class StartActivityRequest {

    private String activityName;

    public StartActivityRequest(String activityName) {
        this.activityName = activityName;
    }


    public String getActivityName() {
        return activityName;
    }


    public StartActivityRequest() {
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
