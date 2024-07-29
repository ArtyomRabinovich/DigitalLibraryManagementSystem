package edu.librarysystem.factories;

import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {

    private final Factory factory = new Factory();

    @Test
    public void testCreateBook() {
        String title = "Test Book";
        String author = "Test Author";
        int pages = 123;
        String isbn = "123-4567890123";
        int yearPublished = 2020;

        Book book = factory.createBook(title, author, pages, isbn, yearPublished);

        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(pages, book.getPages());
        assertEquals(isbn, book.getIsbn());
        assertEquals(yearPublished, book.getYearPublished());
    }

    @Test
    public void testCreateMember() {
        String name = "Test Member";

        Member member = factory.createMember(name);

        assertNotNull(member);
        assertEquals(name, member.getName());
    }
}
