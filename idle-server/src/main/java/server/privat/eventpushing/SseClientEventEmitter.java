package server.privat.eventpushing;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.atomic.AtomicLong;

public class SseClientEventEmitter implements ClientEventEmitter {
    private AtomicLong i = new AtomicLong();
    private SseEmitter sseEmitter;

    private SseClientEventEmitter(SseEmitter sseEmitter) {

        this.sseEmitter = sseEmitter;
    }

    public static SseClientEventEmitter wrap(SseEmitter sseEmitter) {
        return new SseClientEventEmitter(sseEmitter);
    }

    @Override
    public void sendData(String data) {
        SseEmitter.SseEventBuilder sseEvent = SseEmitter.event()
                .data(data)
                .id(String.valueOf(i.incrementAndGet()))
                .name("?? wat hoort hier??");

        try {
            sseEmitter.send(sseEvent);
        } catch (Exception e) {
            sseEmitter.completeWithError(e);
        }

    }

    @Override
    public void onConnectionBroke(Runnable r) {
        sseEmitter.onCompletion(r);
    }
}
