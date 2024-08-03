package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.UserService;

/**
 * The {@code DeleteMemberCommand} class implements the {@code Command} interface
 * and represents a command to delete a member from the library system.
 */
public class DeleteMemberCommand implements Command {
    private final UserService userService;
    private final int memberId;

    /**
     * Constructs a new {@code DeleteMemberCommand} with the specified parameters.
     *
     * @param userService the user service to delete the member from
     * @param memberId    the ID of the member to be deleted
     */
    public DeleteMemberCommand(UserService userService, int memberId) {
        this.userService = userService;
        this.memberId = memberId;
    }

    /**
     * Executes the command to delete a member from the library system.
     */
    @Override
    public void execute() {
        userService.deleteMember(memberId);
    }
}
