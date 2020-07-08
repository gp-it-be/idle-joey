package server.tempexported;

import server.exported.CreateUserRequest;
import server.exported.CreateUserResponse;

public class Controller {



    public CreateUserResponse createUser(CreateUserRequest request){
        //dit wordt
        //return blabla.createUser(); waar blabla contructor injected als interface is bv
        return CreateUserResponse.created(request.getUsername(), "SomeUniqueId");
    }
}
