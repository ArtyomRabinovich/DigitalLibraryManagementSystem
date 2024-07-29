package edu.librarysystem.observers;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.services.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * The BookObserver class observes changes in the status of books and updates the loan status of members.
 */
public class BookObserver implements Observer {
    private  Map<Integer, Integer> loanedBooks; // Map book ID to member ID
    private  UserService userService;

    /**
     * Constructs a new BookObserver with the specified user service.
     *
     * @param userService the user service to interact with members.
     */
    public BookObserver(UserService userService) {
        this.loanedBooks = new HashMap<>();
        this.userService = userService;
    }

    /**
     * Updates the status of the book and the loan status of the member.
     *
     * @param item the library item to be updated.
     */
    @Override
    public void update(LibraryItem item) {
        if (item instanceof Book book) {
            if (book.isAvailable()) {
                Integer memberId = loanedBooks.remove(book.getId());
                if (memberId != null) {
                    Member member = userService.getMember(memberId);
                    if (member != null) {
                        member.removeLoanedBook(book);
                    }
                }
            } else {
                Member member = book.getLoanedTo();
                if (member != null) {
                    loanedBooks.put(book.getId(), member.getId());
                    member.addLoanedBook(book);
                }
            }
            System.out.println("Book status updated: " + book.getTitle());
        }
    }

    /**
     * Retrieves the member who has currently loaned the book with the specified ID.
     *
     * @param bookId the ID of the book.
     * @return the member who has loaned the book, or null if the book is not loaned.
     */
    public Member getLoaningMember(int bookId) {
        Integer memberId = loanedBooks.get(bookId);
        if (memberId != null) {
            return userService.getMember(memberId);
        }
        return null;
    }
}
