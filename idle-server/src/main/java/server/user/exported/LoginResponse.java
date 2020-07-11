package server.user.exported;

public class LoginResponse {


    private boolean success;


    public LoginResponse(boolean success) {
        this.success = success;
    }

    public static LoginResponse userDoesntExist() {
        return new LoginResponse(false);
    }

    public boolean success() {
        return success;
    }


    @Override
    public String toString() {
        return "LoginResponse{" +
                "success=" + success +
                '}';
    }
}
