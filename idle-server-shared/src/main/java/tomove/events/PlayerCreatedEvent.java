package tomove.events;


import lombok.Value;

@Value
public class PlayerCreatedEvent implements JoeyEvent {

    private long tick;
    private String username;


}
