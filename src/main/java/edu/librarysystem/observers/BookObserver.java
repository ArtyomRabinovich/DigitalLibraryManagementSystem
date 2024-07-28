package edu.librarysystem.observers;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.services.UserService;

import java.util.HashMap;
import java.util.Map;

public class BookObserver implements Observer {
    private Map<Integer, Integer> loanedBooks; // Map book ID to member ID
    private UserService userService;

    public BookObserver(UserService userService) {
        this.loanedBooks = new HashMap<>();
        this.userService = userService;
    }

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

    public Member getLoaningMember(int bookId) {
        Integer memberId = loanedBooks.get(bookId);
        if (memberId != null) {
            return userService.getMember(memberId);
        }
        return null;
    }
}
