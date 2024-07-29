package edu.librarysystem.commands;

import edu.librarysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteMemberCommandTest {

    private UserService userService;
    private DeleteMemberCommand command;
    private static final int MEMBER_ID = 1;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        command = new DeleteMemberCommand(userService, MEMBER_ID);
    }

    @Test
    public void testExecute() {
        command.execute();
        verify(userService, times(1)).deleteMember(MEMBER_ID);
    }
}
