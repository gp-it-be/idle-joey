package tomove.cqrs.commandstack;

import lombok.Value;

@Value
public class GoToTickCommand {

    private String playerId;
    private long goToTick;

}
