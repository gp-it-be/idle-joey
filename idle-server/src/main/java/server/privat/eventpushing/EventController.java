package server.privat.eventpushing;


import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
public class EventController {


    public EventController() {
        System.out.println("woopdoo");
    }

    @GetMapping(value = "subscribetoevents", produces = "text/event-stream")
    public Flux<ServerSentEvent<String>> subscribeToEventsFor(){//@RequestHeader("token") String token){
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> {

                    System.out.println("fluxing");
                    return ServerSentEvent.<String> builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("data: qSSE - " + LocalTime.now().toString())
                        .build();})
                .onBackpressureDrop();
    }

}
