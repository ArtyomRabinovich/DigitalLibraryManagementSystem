package edu.librarysystem.commands;

import edu.librarysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddMemberCommandTest {

    private UserService userService;
    private AddMemberCommand command;
    private static final String MEMBER_NAME = "Jane Doe";

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        command = new AddMemberCommand(userService, MEMBER_NAME);
    }

    @Test
    public void testExecute() {
        command.execute();
        verify(userService, times(1)).addMember(MEMBER_NAME);
    }
}
