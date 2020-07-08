package input;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;

public class WrappedWriter {
    private Writer output;

    public WrappedWriter(Writer output) {
        this.output = output;
    }

    void write(String text) {
        try {
            output.write(text);
            output.write(System.lineSeparator());
            output.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
