package server.privat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import requirement.exported.ActivityController;
import requirement.exported.ActivityService;
import requirement.exported.PlayerInfoProvider;
import server.privat.eventpushing.ClientHolder;
import server.privat.eventpushing.EventPushingService;
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
    public UserController userController(InMemorySessionManager sessions) {
        return new UserController(new UserService(new InMemoryUserRepo(), sessions));
    }




    @Bean
    public InMemorySessionManager sessionManager() {
        return new InMemorySessionManager();
    }

    @Bean
    public EventPushingService eventPushingService(ClientHolder clientHolder) {
        return new EventPushingService(clientHolder);
    }


    @Bean
    public ActivityController activityController() {
        PlayerInfoProvider playerInfoProvider = new EveryBodyNoob();
        return new ActivityController(new ActivityService(playerInfoProvider));
    }


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(mvcTaskExecutor());
        configurer.setDefaultTimeout(30_000);
    }

    @Bean
    public ThreadPoolTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("mvc-task-");
        return taskExecutor;
    }

}
