package tomove.cqrs.commandstack;

import lombok.Value;

@Value
public class CreatePlayerCommand {


    private String id;
    private long tick;

}
