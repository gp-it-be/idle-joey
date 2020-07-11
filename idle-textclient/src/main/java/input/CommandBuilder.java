package input;

import commands.Command;
import server.user.tempexported.Controller;

import java.util.Arrays;
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

        String[] splat = input.split(" ");
        String commandName = splat[0];
        String[] parameters = Arrays.copyOfRange(splat, 1, splat.length);

        if(commandName.equals("create")){
            verifyParamLength(parameters, 2);
            return Optional.of(new CreateUserCommand(controller, parameters[0], parameters[1]));
        }

        if(commandName.equals("login")){
            verifyParamLength(parameters, 2);
            return Optional.of(new LoginUserCommand(controller, parameters[0], parameters[1]));
        }


        return Optional.empty();
    }

    private static void verifyParamLength(String[] parameters, int expectedAmount) {

        if(expectedAmount != parameters.length){
            throw new RuntimeException("not the correcte amount of parameters. Expected " + expectedAmount + " but got " + parameters.length);
        }
    }
}
