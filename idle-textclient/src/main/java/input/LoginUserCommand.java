package input;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import commands.Command;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import requestresponses.LoginRequest;
import requestresponses.LoginResponse;
import client.Client;
import state.TokenHolder;

import java.net.URI;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;

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
        if(response.getSuccess()){
            TokenHolder.token = response.getToken();


            EventHandler eventHandler = new SimpleEventHandler();
            String url = String.format("http://localhost:8080/subscribetoevents");
            EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url))
                    .reconnectTime(Duration.ofMillis(3000));

            try (EventSource eventSource = builder.build()) {
                eventSource.start();

                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        output.write(response.toString());

    }
    }
}
