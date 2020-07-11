package input;

import commands.Command;
import requestresponses.CreateUserRequest;
import requestresponses.CreateUserResponse;
import server.tempexported.Client;

public class CreateUserCommand implements Command {
    private Client client;
    private final String username;
    private final String password;

    CreateUserCommand(Client client, String username, String password) {
        this.client = client;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute(WrappedWriter output) {
        CreateUserResponse response = client.createUser(new CreateUserRequest(username, password));
        output.write(response.toString());

    }
}
