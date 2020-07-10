package server.user.exported;

public class CreateUserResponse {
    private boolean success;
    private String username;
    private String someUniqueId;


    public static CreateUserResponse created(String username, String someUniqueId) {
        CreateUserResponse response = new CreateUserResponse();
        response.success = true;
        response.username = username;
        response.someUniqueId = someUniqueId;
        return response;
    }

    public static CreateUserResponse notCreated() {
        CreateUserResponse response = new CreateUserResponse();
        response.success = false;
        return response;
    }


    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "username='" + username + '\'' +
                ", someUniqueId='" + someUniqueId + '\'' +
                '}';
    }
}
