package edu.librarysystem.commands;

import edu.librarysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code DeleteMemberCommand}.
 */
public class DeleteMemberCommandTest {

    private UserService userService;
    private DeleteMemberCommand command;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        command = new DeleteMemberCommand(userService, 1);
    }

    /**
     * Tests the {@code execute} method of {@code DeleteMemberCommand}.
     * Verifies that the {@code deleteMember} method of {@code UserService} is called with the correct parameter.
     */
    @Test
    public void testExecute() {
        command.execute();
        Mockito.verify(userService).deleteMember(1);
    }
}
