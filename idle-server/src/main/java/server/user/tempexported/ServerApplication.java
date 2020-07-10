package server.user.tempexported;

import server.user.InMemoryUserRepo;
import server.user.UserService;

public class ServerApplication {

    private static Controller controller;

    public static void main(String[] args) {


        controller = new Controller(new UserService(new InMemoryUserRepo()));
    }


    public static Controller getController() {
        return controller;
    }
}
