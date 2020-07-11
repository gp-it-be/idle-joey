package requestresponses;


public class CreateUserRequest {

    private String username;
    private String passWord;

    public CreateUserRequest(String username, String passWord) {
        this.username = username;
        this.passWord = passWord;
    }

    public String getUsername() {
        return username;
    }

    public String getPassWord() {
        return passWord;
    }
}
