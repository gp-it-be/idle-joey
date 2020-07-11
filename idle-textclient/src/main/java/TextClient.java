import executor.CommandProvider;
import input.CommandBuilder;
import input.ConsoleCommandProvider;
import input.WrappedWriter;
import server.tempexported.ServerApplication;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextClient {

    public static void main(String[] args) {
        ServerApplication.main(null);


        CommandBuilder.setController(ServerApplication.getController());

        WrappedWriter consoleOutputWriter = new WrappedWriter(new BufferedWriter(new PrintWriter(System.out)));
        CommandProvider commandProvider= new ConsoleCommandProvider(new Scanner(System.in),
                consoleOutputWriter);
        while(true){
            commandProvider.nextCommand().execute(consoleOutputWriter);
        }

    }

}
