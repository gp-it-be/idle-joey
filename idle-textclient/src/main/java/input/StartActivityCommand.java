package input;

import commands.Command;
import requestresponses.StartActivityResponse;
import server.tempexported.Client;
import state.TokenHolder;

public class StartActivityCommand implements Command {
    private Client client;
    private final String activityName;

    StartActivityCommand(Client client, String activityName) {
        this.client = client;
        this.activityName = activityName;
    }

    @Override
    public void execute(WrappedWriter output) {
        StartActivityResponse response = client.startActivity(TokenHolder.token, activityName);
        output.write(response.toString());

    }
}
