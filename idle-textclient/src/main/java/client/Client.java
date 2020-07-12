package client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import requestresponses.*;

public interface Client {


    @RequestLine("POST /createuser")
    CreateUserResponse createUser(CreateUserRequest request);

    @RequestLine("POST /login")
    LoginResponse login(LoginRequest request);


    @RequestLine("POST /login")
    @Headers("token: {token}")
    StartActivityResponse startActivity(StartActivityRequest request, @Param("token") String token);
}
