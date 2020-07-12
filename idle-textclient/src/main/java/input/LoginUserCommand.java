package input;

import commands.Command;
import requestresponses.LoginRequest;
import requestresponses.LoginResponse;
import client.Client;
import state.TokenHolder;

public class LoginUserCommand implements Command {
    private Client client;
    private final String username;
    private final String password;

    LoginUserCommand(Client client, String username, String password) {
        this.client = client;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute(WrappedWriter output) {
        LoginResponse response = client.login(new LoginRequest(username, password));
        if(response.success()){
            TokenHolder.token = response.getToken();
        }
        output.write(response.toString());

    }
}
