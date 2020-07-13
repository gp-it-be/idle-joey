package input;

import com.launchdarkly.eventsource.EventSource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class IncomingEvents {


    private static EventSource eventSource;
    private static Thread eventListenerThread;
    private static AtomicBoolean cancelled = new AtomicBoolean(false);

    public static void start(EventSource.Builder builder) {
        cancelled.set(false);
        eventListenerThread = new Thread(() -> {
            eventSource = builder.build();
            try {
                eventSource.start();
                try {
                    for (int i = 0; i < 60 * 6 && !cancelled.get(); i++) {
                        TimeUnit.MINUTES.sleep(1);
                    }
                    System.out.println("stopping because I got cancelled");
                } catch (InterruptedException e) {
                    System.out.println("client event thread stopped");
                    e.printStackTrace();
                }

            } finally {
                eventSource.close();
            }

        });
        eventListenerThread.start();
    }

    public static void stop() {
        cancelled.set(true);
        //eventSource.close();
    }

}
