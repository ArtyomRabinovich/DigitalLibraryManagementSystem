package edu.librarysystem.invoker;

import edu.librarysystem.interfaces.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code LibrarySystem}.
 */
public class LibrarySystemTest {

    private LibrarySystem librarySystem;

    @BeforeEach
    public void setUp() {
        librarySystem = new LibrarySystem();
    }

    /**
     * Tests the {@code addCommand} method of {@code LibrarySystem}.
     * Verifies that the command is added to the command list and executed.
     */
    @Test
    public void testAddCommand() {
        Command command = Mockito.mock(Command.class);
        librarySystem.addCommand(command);
        Mockito.verify(command).execute();
    }

    /**
     * Tests the {@code executeCommand} method of {@code LibrarySystem}.
     * Verifies that the command is executed if it is not null.
     */
    @Test
    public void testExecuteCommand() {
        Command command = Mockito.mock(Command.class);
        librarySystem.addCommand(command);
        Mockito.verify(command).execute();
    }

    /**
     * Tests that no exception is thrown if the command is null.
     */
    @Test
    public void testExecuteNullCommand() {
        Command command = null;
        librarySystem.addCommand(null);
    }
}
