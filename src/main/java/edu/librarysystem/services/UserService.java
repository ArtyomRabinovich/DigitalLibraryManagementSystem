package edu.librarysystem.services;

import edu.librarysystem.models.Member;
import edu.librarysystem.singleton.Library;

import java.util.List;

public class UserService {
    private final Library library = Library.getInstance();

    public  void addMember(String member) {
        library.addMember(member);
    }

    public  void deleteMember(int id) {
        library.deleteMember(id);
    }

    public  Member getMember(int id) {
        return library.getMember(id);
    }

    public  List<Member> getAllMembers() {
        return library.getAllMembers();
    }
}
