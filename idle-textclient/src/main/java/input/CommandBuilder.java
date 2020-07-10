package input;

import commands.Command;
import server.user.tempexported.Controller;

import java.util.Optional;

public class CommandBuilder {

    private static Controller controller;


    public static void setController(Controller controller) {
        if(controller == null){
            throw new RuntimeException("Provide a non null controller");
        }
        CommandBuilder.controller = controller;
    }

    public static Optional<Command> buildCommand(String input) {
        if(controller == null){
            throw new RuntimeException("Provide the controller before building commands");
        }

        if(input.equals("create")){
            return Optional.of(new CreateUserCommand(controller));
        }


        return Optional.empty();
    }
}
