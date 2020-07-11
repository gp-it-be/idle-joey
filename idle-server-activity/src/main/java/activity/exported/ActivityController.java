package activity.exported;

import ids.ActivityId;
import requestresponses.StartActivityRequest;
import requestresponses.StartActivityResponse;

public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    //TODO addItemToActivityContainer(acitivty, item)

    public StartActivityResponse startActivity(String username, StartActivityRequest request) {
        ActivityId activityId = ActivityId.valueOf(request.getActivityName());
        return activityService.startActivity(username, activityId);
    }
}
