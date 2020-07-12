package client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import requestresponses.*;

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
}
