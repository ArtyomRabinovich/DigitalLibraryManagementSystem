package edu.librarysystem.invoker;

import edu.librarysystem.interfaces.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code LibrarySystem} class is an invoker that manages and executes commands.
 */
public class LibrarySystem {
    private final List<Command> commandList;

    /**
     * Constructs a new {@code LibrarySystem} with an empty command list.
     */
    public LibrarySystem() {
        this.commandList = new ArrayList<>();
    }

    /**
     * Adds a command to the command list and executes it.
     *
     * @param command the command to be added and executed
     */
    public void addCommand(Command command) {
        commandList.add(command);
        executeCommand(command);
    }

    /**
     * Executes the given command if it is not null.
     *
     * @param command the command to be executed
     */
    private void executeCommand(Command command) {
        if (command != null) {
            command.execute();
        }
    }
}
