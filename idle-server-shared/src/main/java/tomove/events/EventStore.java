package tomove.events;

import java.util.List;

public interface EventStore {

    void storeEvent(JoeyEvent event);


    List<JoeyEvent> getEventsFor(String username);

}
