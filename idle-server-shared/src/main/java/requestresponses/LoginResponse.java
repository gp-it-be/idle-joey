package requestresponses;

public class LoginResponse {


    private boolean success;
    private String token;


    private LoginResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public static LoginResponse userDoesntExist() {
        return new LoginResponse(false, null);
    }

    public static LoginResponse success(String token) {
        return new LoginResponse(true, token);
    }

    public static LoginResponse incorrectPassword() {
        return new LoginResponse(false, null);
    }


    public boolean success() {
        return success;
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
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}
