package input;

import commands.Command;
import server.user.exported.LoginRequest;
import server.user.exported.LoginResponse;
import server.user.tempexported.Controller;

public class LoginUserCommand implements Command {
    private Controller controller;
    private final String username;
    private final String password;

    LoginUserCommand(Controller controller, String username, String password) {
        this.controller = controller;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute(WrappedWriter output) {
        LoginResponse response = controller.loginUser(new LoginRequest(username, password));
        output.write(response.toString());

    }
}
