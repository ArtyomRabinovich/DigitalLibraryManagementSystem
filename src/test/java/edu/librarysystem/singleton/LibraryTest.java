package edu.librarysystem.singleton;

import edu.librarysystem.factories.Factory;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.observers.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryTest {

    private Library library;
    private Factory factory;
    private Observer observer;

    @BeforeEach
    public void setUp() {
        // Reset the singleton instance using reflection to ensure a fresh start for each test
        try {
            var instanceField = Library.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, null);
            library = Library.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        factory = mock(Factory.class);
        observer = mock(Observer.class);

        // Inject the mock factory into the Library instance using reflection
        try {
            var factoryField = Library.class.getDeclaredField("factory");
            factoryField.setAccessible(true);
            factoryField.set(library, factory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSingletonInstance() {
        Library instance1 = Library.getInstance();
        Library instance2 = Library.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    public void testAddObserver() {
        library.addObserver(observer);
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);
        library.addBook("Title", "Author", 100, "ISBN", 2020);
        verify(observer, times(1)).update(any(Book.class));
    }

    @Test
    public void testRemoveObserver() {
        library.addObserver(observer);
        library.removeObserver(observer);
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);
        library.addBook("Title", "Author", 100, "ISBN", 2020);
        verify(observer, never()).update(any(Book.class));
    }

    @Test
    public void testAddBook() {
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);

        library.addBook("Title", "Author", 100, "ISBN", 2020);

        Book book = library.getAllBooks().get(0);
        assertNotNull(book, "Book should not be null");
        assertEquals("Title", book.getTitle());
    }

    @Test
    public void testDeleteBook() {
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);

        library.addBook("Title", "Author", 100, "ISBN", 2020);
        Book book = library.getAllBooks().get(0);

        library.deleteBook(book.getId());
        assertTrue(library.getAllBooks().isEmpty(), "Library should be empty after deletion");
    }

    @Test
    public void testLoanBook() {
        Member mockMember = new Member("Member");
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);

        when(factory.createMember("Member")).thenReturn(mockMember);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);

        library.addMember("Member");
        Member member = library.getAllMembers().get(0);

        library.addBook("Title", "Author", 100, "ISBN", 2020);
        Book book = library.getAllBooks().get(0);

        library.loanBook(book.getId(), member.getId());

        assertFalse(book.isAvailable(), "Book should be loaned out");
        assertEquals(member, book.getLoanedTo(), "Book should be loaned to the member");
    }

    @Test
    public void testReturnBook() {
        Member mockMember = new Member("Member");
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);

        when(factory.createMember("Member")).thenReturn(mockMember);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);

        library.addMember("Member");
        Member member = library.getAllMembers().get(0);

        library.addBook("Title", "Author", 100, "ISBN", 2020);
        Book book = library.getAllBooks().get(0);

        library.loanBook(book.getId(), member.getId());
        library.returnBook(book.getId());

        assertTrue(book.isAvailable(), "Book should be available");
        assertNull(book.getLoanedTo(), "Book should not be loaned to anyone");
    }

    @Test
    public void testAddMember() {
        Member mockMember = new Member("Member");
        when(factory.createMember("Member")).thenReturn(mockMember);

        library.addMember("Member");

        Member member = library.getAllMembers().get(0);
        assertNotNull(member, "Member should not be null");
        assertEquals("Member", member.getName());
    }

    @Test
    public void testDeleteMember() {
        Member mockMember = new Member("Member");
        when(factory.createMember("Member")).thenReturn(mockMember);

        library.addMember("Member");
        Member member = library.getAllMembers().get(0);

        library.deleteMember(member.getId());
        assertTrue(library.getAllMembers().isEmpty(), "Library should be empty after deletion");
    }

    @Test
    public void testGetSummary() {
        Member mockMember = new Member("Member");
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);

        when(factory.createMember("Member")).thenReturn(mockMember);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);

        library.addMember("Member");
        library.addBook("Title", "Author", 100, "ISBN", 2020);

        String summary = library.getSummary();
        assertTrue(summary.contains("Title: Title"), "Summary should contain book title");
        assertTrue(summary.contains("Name: Member"), "Summary should contain member name");
    }

    @Test
    public void testCloneBook() {
        Book mockBook = new Book("Title", "Author", 100, "ISBN", 2020);
        when(factory.createBook("Title", "Author", 100, "ISBN", 2020)).thenReturn(mockBook);

        library.addBook("Title", "Author", 100, "ISBN", 2020);
        Book book = library.getAllBooks().get(0);

        Book clonedBook = library.cloneBook(book.getId());
        assertNotSame(book, clonedBook, "Cloned book should be a different instance");
        assertEquals(book.getTitle(), clonedBook.getTitle(), "Cloned book should have the same title");
    }

    @Test
    public void testGetBooksSortedBy() {
        Book mockBook1 = new Book("TitleB", "AuthorC", 150, "ISBN1", 2021);
        Book mockBook2 = new Book("TitleA", "AuthorB", 200, "ISBN2", 2019);
        Book mockBook3 = new Book("TitleC", "AuthorA", 100, "ISBN3", 2020);

        when(factory.createBook("TitleB", "AuthorC", 150, "ISBN1", 2021)).thenReturn(mockBook1);
        when(factory.createBook("TitleA", "AuthorB", 200, "ISBN2", 2019)).thenReturn(mockBook2);
        when(factory.createBook("TitleC", "AuthorA", 100, "ISBN3", 2020)).thenReturn(mockBook3);

        library.addBook("TitleB", "AuthorC", 150, "ISBN1", 2021);
        library.addBook("TitleA", "AuthorB", 200, "ISBN2", 2019);
        library.addBook("TitleC", "AuthorA", 100, "ISBN3", 2020);

        // Sort by title
        List<Book> sortedByTitle = library.getBooksSortedBy(Comparator.comparing(Book::getTitle));
        assertEquals("TitleA", sortedByTitle.get(0).getTitle());
        assertEquals("TitleB", sortedByTitle.get(1).getTitle());
        assertEquals("TitleC", sortedByTitle.get(2).getTitle());

        // Sort by author
        List<Book> sortedByAuthor = library.getBooksSortedBy(Comparator.comparing(Book::getAuthor));
        assertEquals("AuthorA", sortedByAuthor.get(0).getAuthor());
        assertEquals("AuthorB", sortedByAuthor.get(1).getAuthor());
        assertEquals("AuthorC", sortedByAuthor.get(2).getAuthor());

        // Sort by pages
        List<Book> sortedByPages = library.getBooksSortedBy(Comparator.comparingInt(Book::getPages));
        assertEquals(100, sortedByPages.get(0).getPages());
        assertEquals(150, sortedByPages.get(1).getPages());
        assertEquals(200, sortedByPages.get(2).getPages());

        // Sort by year published
        List<Book> sortedByYearPublished = library.getBooksSortedBy(Comparator.comparingInt(Book::getYearPublished));
        assertEquals(2019, sortedByYearPublished.get(0).getYearPublished());
        assertEquals(2020, sortedByYearPublished.get(1).getYearPublished());
        assertEquals(2021, sortedByYearPublished.get(2).getYearPublished());
    }

    @Test
    public void testGetMembersSortedBy() {
        Member mockMember1 = new Member("MemberB");
        Member mockMember2 = new Member("MemberA");
        Member mockMember3 = new Member("MemberC");

        when(factory.createMember("MemberB")).thenReturn(mockMember1);
        when(factory.createMember("MemberA")).thenReturn(mockMember2);
        when(factory.createMember("MemberC")).thenReturn(mockMember3);

        library.addMember("MemberB");
        library.addMember("MemberA");
        library.addMember("MemberC");

        Member memberB = library.getAllMembers().get(0);
        Member memberA = library.getAllMembers().get(1);
        Member memberC = library.getAllMembers().get(2);

        Book book1 = new Book("Title1", "Author1", 100, "ISBN1", 2020);
        Book book2 = new Book("Title2", "Author2", 200, "ISBN2", 2021);

        memberA.addLoanedBook(book1);
        memberA.addLoanedBook(book2);

        memberB.addLoanedBook(book1);

        // Sort by name
        List<Member> sortedByName = library.getMembersSortedBy(Comparator.comparing(Member::getName));
        assertEquals("MemberA", sortedByName.get(0).getName());
        assertEquals("MemberB", sortedByName.get(1).getName());
        assertEquals("MemberC", sortedByName.get(2).getName());

        // Sort by currently loaned books
        List<Member> sortedByCurrentlyLoaned = library.getMembersSortedBy(Comparator.comparingInt(Member::getCurrentlyLoanedBooks));
        assertEquals("MemberC", sortedByCurrentlyLoaned.get(0).getName());
        assertEquals("MemberB", sortedByCurrentlyLoaned.get(1).getName());
        assertEquals("MemberA", sortedByCurrentlyLoaned.get(2).getName());

        // Sort by total books loaned
        List<Member> sortedByTotalLoaned = library.getMembersSortedBy(Comparator.comparingInt(Member::getTotalBooksLoaned));
        assertEquals("MemberC", sortedByTotalLoaned.get(0).getName());
        assertEquals("MemberB", sortedByTotalLoaned.get(1).getName());
        assertEquals("MemberA", sortedByTotalLoaned.get(2).getName());
    }
}
