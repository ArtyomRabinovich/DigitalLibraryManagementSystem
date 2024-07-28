package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.UserService;

public class DeleteMemberCommand implements Command {
    private UserService userService;
    private int memberId;

    public DeleteMemberCommand(UserService userService, int memberId) {
        this.userService = userService;
        this.memberId = memberId;
    }

    @Override
    public void execute() {
        synchronized (userService) {
            userService.deleteMember(memberId);
        }
    }
}
