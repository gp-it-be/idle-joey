package input;

import commands.Command;
import server.user.exported.CreateUserRequest;
import server.user.exported.CreateUserResponse;
import server.user.tempexported.Controller;

public class CreateUserCommand implements Command {
    private Controller controller;
    private final String username;
    private final String password;

    CreateUserCommand(Controller controller, String username, String password) {
        this.controller = controller;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute(WrappedWriter output) {
        CreateUserResponse response = controller.createUser(new CreateUserRequest(username, password));
        output.write(response.toString());

    }
}
