package input;

import commands.Command;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import requestresponses.LoginRequest;
import requestresponses.LoginResponse;
import client.Client;
import state.TokenHolder;

import java.time.LocalTime;

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

            WebClient client = WebClient.create("http://localhost:8080/");
            ParameterizedTypeReference<ServerSentEvent<String>> type
                    = new ParameterizedTypeReference<ServerSentEvent<String>>() {};

            Flux<ServerSentEvent<String>> eventStream = client.get()
                    .uri("subscribetoevents")
                    .header("token", response.getToken())
            .header("Content-Type","text/event-stream")
            .header("Cache-Control","no-cache")
                    .retrieve()
                    .bodyToFlux(type);

            eventStream.subscribe(
                    content -> output.write("got sometgig"),
                    error -> output.write("error"),
                    () -> output.write("Completed!!!"));

        }
        output.write(response.toString());

    }
}
