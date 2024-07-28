package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.UserService;
import edu.librarysystem.models.Member;

public class AddMemberCommand implements Command {
    private UserService userService;
    private String member;

    public AddMemberCommand(UserService userService, String member) {
        this.userService = userService;
        this.member = member;
    }

    @Override
    public void execute() {
        synchronized (userService) {
            userService.addMember(member);
        }
    }
}
