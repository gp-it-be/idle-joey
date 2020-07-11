package requestresponses;

public class StartActivityResponse {
    private boolean success;
    private String reason;
    private String startedActivity;


    public static StartActivityResponse started(String activity) {
        StartActivityResponse response = new StartActivityResponse();
        response.success = true;
        response.startedActivity = activity;
        return response;
    }

    public static StartActivityResponse cantStart(String reason) {
        StartActivityResponse response = new StartActivityResponse();
        response.success = false;
        response.reason = reason;
        return response;
    }

    @Override
    public String toString() {
        return "StartActivityResponse{" +
                "success=" + success +
                ", reason='" + reason + '\'' +
                ", startedActivity='" + startedActivity + '\'' +
                '}';
    }
}
