package commands;

import input.WrappedWriter;

public interface Command {

    void execute(WrappedWriter output);

}
