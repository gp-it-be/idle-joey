package server.exported;

public class CreateUserResponse {
    private final String username;
    private final String someUniqueId;

    private CreateUserResponse(String username, String someUniqueId) {

        this.username = username;
        this.someUniqueId = someUniqueId;
    }

    public static CreateUserResponse created(String username, String someUniqueId) {
        return new CreateUserResponse(username, someUniqueId);
    }


    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "username='" + username + '\'' +
                ", someUniqueId='" + someUniqueId + '\'' +
                '}';
    }
}
