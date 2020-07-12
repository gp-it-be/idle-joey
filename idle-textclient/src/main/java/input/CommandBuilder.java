package input;

import client.Client;
import commands.Command;

import java.util.Arrays;
import java.util.Optional;

public class CommandBuilder {

    private static Client client;


    public static void setClient(Client client) {
        if(client == null){
            throw new RuntimeException("Provide a non null client");
        }
        CommandBuilder.client = client;
    }

    public static Optional<Command> buildCommand(String input) {
        if(client == null){
            throw new RuntimeException("Provide the client before building commands");
        }

        String[] splat = input.split(" ");
        String commandName = splat[0];
        String[] parameters = Arrays.copyOfRange(splat, 1, splat.length);

        if(commandName.equals("create")){
            verifyParamLength(parameters, 2);
            return Optional.of(new CreateUserCommand(client, parameters[0], parameters[1]));
        }

        if(commandName.equals("login")){
            verifyParamLength(parameters, 2);
            return Optional.of(new LoginUserCommand(client, parameters[0], parameters[1]));
        }

        if(commandName.equals("start")){
            verifyParamLength(parameters, 1);
            return Optional.of(new StartActivityCommand(client, parameters[0]));
        }


        return Optional.empty();
    }

    private static void verifyParamLength(String[] parameters, int expectedAmount) {

        if(expectedAmount != parameters.length){
            throw new RuntimeException("not the correcte amount of parameters. Expected " + expectedAmount + " but got " + parameters.length);
        }
    }
}
