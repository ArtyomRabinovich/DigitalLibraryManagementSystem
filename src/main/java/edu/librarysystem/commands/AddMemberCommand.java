package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.UserService;

/**
 * The {@code AddMemberCommand} class implements the {@code Command} interface
 * and represents a command to add a new member to the library system.
 */
public class AddMemberCommand implements Command {
    private final UserService userService;
    private final String member;

    /**
     * Constructs a new {@code AddMemberCommand} with the specified parameters.
     *
     * @param userService the user service to add the member to
     * @param member      the name of the member to be added
     */
    public AddMemberCommand(UserService userService, String member) {
        this.userService = userService;
        this.member = member;
    }

    /**
     * Executes the command to add a new member to the library system.
     */
    @Override
    public void execute() {
        userService.addMember(member);
    }
}
