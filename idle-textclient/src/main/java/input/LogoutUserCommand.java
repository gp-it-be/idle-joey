package input;

import client.Client;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import commands.Command;
import okhttp3.Headers;
import requestresponses.LoginRequest;
import requestresponses.LoginResponse;
import state.TokenHolder;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LogoutUserCommand implements Command {
    private Client client;

    LogoutUserCommand(Client client) {
        this.client = client;
    }

    @Override
    public void execute(WrappedWriter output) {
        LogoutResponse response = client.logout(TokenHolder.token);
        if(response.getSuccess()){
            TokenHolder.clearToken();
        }
    }
}
