package edu.librarysystem.commands;

import edu.librarysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code AddMemberCommand}.
 */
public class AddMemberCommandTest {

    private UserService userService;
    private AddMemberCommand command;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        command = new AddMemberCommand(userService, "Test Member");
    }

    /**
     * Tests the {@code execute} method of {@code AddMemberCommand}.
     * Verifies that the {@code addMember} method of {@code UserService} is called with the correct parameter.
     */
    @Test
    public void testExecute() {
        command.execute();
        Mockito.verify(userService).addMember("Test Member");
    }
}
