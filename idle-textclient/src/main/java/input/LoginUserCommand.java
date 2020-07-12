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
        if (response.getSuccess()) {
            TokenHolder.token = response.getToken();


            EventHandler eventHandler = new SimpleEventHandler();
            String url = String.format("http://localhost:8080/subscribetoevents");
            EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url))
                    .reconnectTime(Duration.ofMillis(3000));
            builder.headers(Headers.of("token", response.getToken()));

            new Thread(() -> {
                try (EventSource eventSource = builder.build()) {
                    eventSource.start();
                    try {
                        TimeUnit.HOURS.sleep(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }).start();

            output.write(response.toString());

        }
    }
}
