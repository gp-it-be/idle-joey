package requestresponses;


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

    public boolean success() {
        return success;
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "username='" + username + '\'' +
                ", someUniqueId='" + someUniqueId + '\'' +
                '}';
    }


    public boolean isSuccess() {
        return success;
    }

    public String getUsername() {
        return username;
    }

    public String getSomeUniqueId() {
        return someUniqueId;
    }
}
