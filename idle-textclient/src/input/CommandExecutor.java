package input;

import java.util.Scanner;

public class CommandExecutor {
    private Scanner scanner;

    public CommandExecutor(Scanner scanner) {

        this.scanner = scanner;
    }

    public void untilCancelled() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
