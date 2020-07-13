package client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import reactor.core.publisher.Flux;
import requestresponses.*;
import org.springframework.http.codec.ServerSentEvent;

public interface Client {


    @RequestLine("POST /createuser")
    @Headers("Content-Type: application/json")
    CreateUserResponse createUser(CreateUserRequest request);

    @RequestLine("POST /login")
    @Headers("Content-Type: application/json")
    LoginResponse login(LoginRequest request);


    @RequestLine("POST /login")
    @Headers({"token: {token}","Content-Type: application/json"})
    StartActivityResponse startActivity(StartActivityRequest request, @Param("token") String token);


    @RequestLine("GET /subscribetoevents")
    @Headers({"token: {token}","Content-Type: application/json"})
    Flux<ServerSentEvent> subscribeToEvents(@Param("token") String token);

    @RequestLine("GET /logout")
    @Headers({"token: {token}","Content-Type: application/json"})
    LogoutResponse logout(@Param("token")String token);
}
