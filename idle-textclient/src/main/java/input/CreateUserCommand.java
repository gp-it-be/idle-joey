package input;

import commands.Command;
import server.user.exported.CreateUserRequest;
import server.user.exported.CreateUserResponse;
import server.user.tempexported.Controller;

public class CreateUserCommand implements Command {
    private Controller controller;

    CreateUserCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void execute(WrappedWriter output) {
        CreateUserResponse response = controller.createUser(new CreateUserRequest("Joske45"));
        output.write(response.toString());

    }
}
