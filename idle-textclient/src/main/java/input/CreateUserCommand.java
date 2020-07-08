package input;

import commands.Command;
import server.exported.CreateUserRequest;
import server.exported.CreateUserResponse;
import server.tempexported.Controller;

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
