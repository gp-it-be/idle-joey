package input;

import client.Client;
import commands.Command;
import requestresponses.LogoutResponse;
import state.TokenHolder;

public class LogoutUserCommand implements Command {
    private Client client;

    LogoutUserCommand(Client client) {
        this.client = client;
    }

    @Override
    public void execute(WrappedWriter output) {
        LogoutResponse response = client.logout(TokenHolder.token);
        if (response.getSuccess()) {
            IncomingEvents.stop();
            TokenHolder.clearToken();
            output.write("logged out");
        }
    }
}
