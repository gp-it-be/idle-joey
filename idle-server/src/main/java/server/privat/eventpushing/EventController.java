package server.privat.eventpushing;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class EventController {


    private EventPushingService eventPushingService;


    public EventController(EventPushingService eventPushingService) {
        this.eventPushingService = eventPushingService;
    }

    @GetMapping(value = "subscribetoevents", produces = "text/event-stream")
    public SseEmitter subscribeToEventsFor(@RequestHeader("token") String token) {
        SseEmitter emitter = new SseEmitter();
        eventPushingService.registerEmitter(token, SseClientEventEmitter.wrap(emitter));
        return emitter;
    }

}
