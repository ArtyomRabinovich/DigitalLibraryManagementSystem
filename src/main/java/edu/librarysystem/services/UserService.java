package edu.librarysystem.services;

import edu.librarysystem.models.Member;
import edu.librarysystem.singleton.Library;

import java.util.List;

/**
 * The {@code UserService} class provides services to manage members of the library.
 */
public class UserService {
    private final Library library = Library.getInstance();

    /**
     * Adds a new member to the library.
     *
     * @param member the name of the member to add
     */
    public void addMember(String member) {
        library.addMember(member);
    }

    /**
     * Deletes a member from the library.
     *
     * @param id the ID of the member to delete
     */
    public void deleteMember(int id) {
        library.deleteMember(id);
    }

    /**
     * Retrieves a member from the library.
     *
     * @param id the ID of the member to retrieve
     * @return the {@code Member} object representing the member, or {@code null} if not found
     */
    public Member getMember(int id) {
        return library.getMember(id);
    }

    /**
     * Retrieves all members from the library.
     *
     * @return a list of all members in the library
     */
    public List<Member> getAllMembers() {
        return library.getAllMembers();
    }
}
