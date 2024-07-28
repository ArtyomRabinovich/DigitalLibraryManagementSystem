package edu.librarysystem.invoker;

import edu.librarysystem.interfaces.Command;

import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private final List<Command> commandList;

    public LibrarySystem() {
        this.commandList = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commandList.add(command);
        executeCommand(command);
    }

    private void executeCommand(Command command) {
        if (command != null) {
            command.execute();
        }
    }

    public void shutdown() {
        System.out.println("Shutting down the command system...");
    }
}
