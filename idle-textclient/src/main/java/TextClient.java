import client.Client;
import executor.CommandProvider;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import input.CommandBuilder;
import input.ConsoleCommandProvider;
import input.WrappedWriter;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextClient {

    public static void main(String[] args) {
        Client client = buildClient();
        CommandBuilder.setClient(client);

        WrappedWriter consoleOutputWriter = new WrappedWriter(new BufferedWriter(new PrintWriter(System.out)));
        CommandProvider commandProvider= new ConsoleCommandProvider(new Scanner(System.in),
                consoleOutputWriter);
        while(true){
            try {
                commandProvider.nextCommand().execute(consoleOutputWriter);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Command failed: " + e.getMessage());
            }
        }

    }

    private static Client buildClient() {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(Client.class, "http://localhost:8080");
    }

}
