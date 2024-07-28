package edu.librarysystem.commands;

import edu.librarysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class DeleteMemberCommandTest {
    private UserService userService;
    private int memberId;
    private DeleteMemberCommand deleteMemberCommand;

    @BeforeEach
    public void setUp() {
        // Mocking UserService to isolate the test
        userService = Mockito.mock(UserService.class);

        // Using a real integer value for memberId
        memberId = 456; // example memberId

        // Initializing the command with the mocked service and real memberId
        deleteMemberCommand = new DeleteMemberCommand(userService, memberId);
    }

    @Test
    public void testExecute() {
        // Executing the command
        deleteMemberCommand.execute();

        // Verifying that deleteMember was called exactly once with the correct memberId
        verify(userService, times(1)).deleteMember(memberId);
    }

    @Test
    public void testExecuteSynchronization() {
        // Ensuring the command executes within a synchronized block
        synchronized (userService) {
            deleteMemberCommand.execute();
            verify(userService, times(1)).deleteMember(memberId);
        }
    }
}
