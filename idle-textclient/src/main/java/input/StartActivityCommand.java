package input;

import commands.Command;
import requestresponses.StartActivityRequest;
import requestresponses.StartActivityResponse;
import client.Client;
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
        StartActivityResponse response = client.startActivity(new StartActivityRequest(activityName), TokenHolder.token);
        output.write(response.toString());

    }
}
