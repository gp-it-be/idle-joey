package requestresponses;

public class LogoutResponse {


    private boolean success;


    public LogoutResponse() {
    }

    private LogoutResponse(boolean success) {
        this.success = success;
    }

    public static LogoutResponse success() {
        return new LogoutResponse(true);
    }


    public boolean getSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

}
