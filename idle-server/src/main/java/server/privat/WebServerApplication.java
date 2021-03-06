package server.privat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import requirement.exported.ActivityController;
import requirement.exported.ActivityService;
import requirement.exported.PlayerInfoProvider;
import server.privat.eventpushing.ClientCommunications;
import server.privat.eventpushing.EventPushingService;
import server.privat.events.ClientUpdatingInMemoryEventStore;
import server.privat.tomove.Ticker;
import tomove.cqrs.commandstack.PlayerAggregator;
import user.exported.UserController;
import user.exported.UserService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
@Configuration
@EnableScheduling
public class WebServerApplication implements WebMvcConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }

    @Bean
    public UserController userController(InMemorySessionManagement sessions, PlayerAggregator playerAggregator) {
        return new UserController(new UserService(new InMemoryUserRepo(), sessions, playerAggregator));
    }


    @Bean
    public InMemorySessionManagement sessionManager() {
        return new InMemorySessionManagement();
    }

    @Bean
    public EventPushingService eventPushingService(ClientCommunications clientCommunications) {
        return new EventPushingService(clientCommunications);
    }


    @Bean
    public ActivityController activityController() {
        PlayerInfoProvider playerInfoProvider = new EveryBodyNoob();
        return new ActivityController(new ActivityService(playerInfoProvider));
    }


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(mvcTaskExecutor());
        configurer.setDefaultTimeout(60 * 60_000);
    }

    @Bean
    public ThreadPoolTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("mvc-task-");
        return taskExecutor;
    }

    @Bean
    public PlayerAggregator playerAggregator(ClientCommunications clientCommunications) {
        return new PlayerAggregator(new ClientUpdatingInMemoryEventStore(clientCommunications));
    }

    @Bean
    public Ticker ticker(ConnectedUsernames connectedUsernames, PlayerAggregator playerAggregator){
        return new Ticker(connectedUsernames, playerAggregator);
    }
}
