package edu.librarysystem;

import edu.librarysystem.comparators.*;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.singleton.Librarian;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Get the instance of the Librarian singleton
        Librarian librarian = Librarian.getInstance();

        // Add some members
        librarian.addMember("Alice");
        librarian.addMember("Bob");

        // Add some books
        librarian.addBook("Effective Java", "Joshua Bloch", 416, "978-0134685991", 2018);
        librarian.addBook("Clean Code", "Robert C. Martin", 464, "978-0132350884", 2008);

        // Display library summary
        System.out.println("Library Summary:\n" + librarian.getLibrarySummary());

        // Get IDs for the books
        Book book1 = librarian.getBooksSortedBy(new BookTitleComparator()).get(0);
        Book book2 = librarian.getBooksSortedBy(new BookTitleComparator()).get(1);
        int book1Id = book1.getId();
        int book2Id = book2.getId();

        // Loan a book to a member
        librarian.loanBook(book1Id, librarian.getMembersSortedBy(new MemberBooksLoanedComparator()).get(0).getId()); // Loan "Effective Java" to Alice
        System.out.println("Library Summary after loaning a book:\n" + librarian.getLibrarySummary());

        // Return the book
        librarian.returnBook(book1Id); // Return "Effective Java"
        System.out.println("Library Summary after returning the book:\n" + librarian.getLibrarySummary());

        // Delete a book
        librarian.deleteBook(book2Id); // Delete "Clean Code"
        System.out.println("Library Summary after deleting a book:\n" + librarian.getLibrarySummary());

        // Delete a member
        librarian.deleteMember(librarian.getMembersSortedBy(new MemberBooksLoanedComparator()).get(1).getId()); // Delete Bob
        System.out.println("Library Summary after deleting a member:\n" + librarian.getLibrarySummary());

        // Clone a book
        Book clonedBook = librarian.duplicateBook(book1Id);
        if (clonedBook != null) {
            System.out.println("Cloned Book: " + clonedBook.getTitle());
        }

        // Display all books sorted by title
        System.out.println("All Books in Library sorted by title:");
        List<Book> sortedBooksByTitle = librarian.getBooksSortedBy(new BookTitleComparator());
        for (Book book : sortedBooksByTitle) {
            System.out.println(book.getTitle());
        }

        // Display all members sorted by name
        System.out.println("All Members in Library sorted by name:");
        List<Member> sortedMembersByName = librarian.getMembersSortedBy(new MemberTotalBooksLoanedComparator());
        for (Member member : sortedMembersByName) {
            System.out.println(member.getName());
        }
    }
}
