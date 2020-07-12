package requestresponses;

public class LoginResponse {


    private boolean success;
    private String token;


    public LoginResponse() {
    }

    private LoginResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public static LoginResponse userDoesntExist() {
        return new LoginResponse(false, null);
    }

    public static LoginResponse getSuccess(String token) {
        return new LoginResponse(true, token);
    }

    public static LoginResponse incorrectPassword() {
        return new LoginResponse(false, null);
    }


    public boolean getSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        if (!success) {
            throw new RuntimeException("There is no token in an unsuccessful login response");
        }
        return token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "getSuccess=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}
