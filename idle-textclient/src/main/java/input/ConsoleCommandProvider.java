package input;

import commands.Command;
import executor.CommandProvider;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleCommandProvider implements CommandProvider {


    private Scanner scanner;
    private WrappedWriter output;

    public ConsoleCommandProvider(Scanner scanner, WrappedWriter output) {
        this.scanner = scanner;
        this.output = output;
    }

    @Override
    public Command nextCommand() {
        String input = scanner.nextLine();
        Optional<Command> command;
        try {
            command = CommandBuilder.buildCommand(input);
        } catch (Exception e) {
            output.write(e.getMessage());
            return nextCommand();
        }
        return command.orElseGet(() ->
        {
            output.write("invalid command");
            return nextCommand();
        });
    }

}
