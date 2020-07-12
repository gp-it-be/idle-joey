package requestresponses;


public class CreateUserRequest {

    private String username;
    private String passWord;

    public CreateUserRequest(String username, String passWord) {
        this.username = username;
        this.passWord = passWord;
    }


    //TODO niet tevreden met lege constuctor & setters, das nodig voor de rest-laag, maar niet voor de service plaats etc
    public CreateUserRequest() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUsername() {
        return username;
    }

    public String getPassWord() {
        return passWord;
    }
}
