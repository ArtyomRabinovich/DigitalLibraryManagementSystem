package edu.librarysystem.factories;

import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@code Factory}.
 */
public class FactoryTest {

    private final Factory factory = new Factory();

    /**
     * Tests the {@code createBook} method of {@code Factory}.
     * Verifies that a {@code Book} instance is created with the correct parameters.
     */
    @Test
    public void testCreateBook() {
        Book book = factory.createBook("Test Title", "Test Author", 123, "1234567890", 2021);
        assertNotNull(book);
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(123, book.getPages());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(2021, book.getYearPublished());
    }

    /**
     * Tests the {@code createMember} method of {@code Factory}.
     * Verifies that a {@code Member} instance is created with the correct name.
     */
    @Test
    public void testCreateMember() {
        Member member = factory.createMember("Test Member");
        assertNotNull(member);
        assertEquals("Test Member", member.getName());
    }
}
