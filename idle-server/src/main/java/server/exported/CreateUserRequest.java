package server.exported;

public class CreateUserRequest {
    private String username;

    public CreateUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
