package server.privat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import requirement.exported.ActivityController;
import requirement.exported.ActivityService;
import requirement.exported.PlayerInfoProvider;
import server.tempexported.Client;
import user.exported.UserController;
import user.exported.UserService;

@SpringBootApplication( exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
@Configuration
public class WebServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }

    @Bean
    public Client client(){
        InMemorySessionManager sessions = new InMemorySessionManager();

        UserController userController = new UserController(new UserService(new InMemoryUserRepo(), sessions));

        PlayerInfoProvider playerInfoProvider = new EveryBodyNoob();


        ActivityController activityController = new ActivityController(new ActivityService(playerInfoProvider));
        return new Client(userController, activityController, sessions);
    }


}
