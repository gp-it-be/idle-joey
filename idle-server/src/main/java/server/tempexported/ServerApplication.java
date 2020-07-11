package server.tempexported;


import requirement.exported.ActivityController;
import requirement.exported.ActivityService;
import requirement.exported.PlayerInfoProvider;
import server.privat.EveryBodyNoob;
import server.privat.InMemorySessionManager;
import server.privat.InMemoryUserRepo;
import user.exported.UserController;
import user.exported.UserService;

public class ServerApplication {

    private static Client client;

    public static void main(String[] args) {

        InMemorySessionManager sessions = new InMemorySessionManager();

        UserController userController = new UserController(new UserService(new InMemoryUserRepo(), sessions));

        PlayerInfoProvider playerInfoProvider = new EveryBodyNoob();


        ActivityController activityController = new ActivityController(new ActivityService(playerInfoProvider));
        client = new Client(userController, activityController, sessions);
    }


    public static Client getClient() {
        return client;
    }
}
