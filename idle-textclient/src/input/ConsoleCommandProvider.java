package input;

import commands.Command;
import executor.CommandProvider;

import java.io.Writer;
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
        return CommandBuilder.buildCommand(input).orElseGet(() ->
        {
            output.write("invalid command");
            return nextCommand();
        });
    }

}
