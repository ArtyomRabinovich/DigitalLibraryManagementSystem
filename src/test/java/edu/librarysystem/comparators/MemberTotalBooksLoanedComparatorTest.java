package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTotalBooksLoanedComparatorTest {

    private MemberTotalBooksLoanedComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new MemberTotalBooksLoanedComparator();
    }

    @Test
    public void testCompare() {
        Member member1 = new Member("Member1");
        Member member2 = new Member("Member2");

        Book book1 = Mockito.mock(Book.class);
        Book book2 = Mockito.mock(Book.class);

        member1.addLoanedBook(book1);
        member1.addLoanedBook(book2);

        member2.addLoanedBook(book1);

        int result = comparator.compare(member1, member2);
        assertTrue(result > 0, "Member1 with 2 total loaned books should be greater than Member2 with 1 total loaned book");

        result = comparator.compare(member2, member1);
        assertTrue(result < 0, "Member2 with 1 total loaned book should be less than Member1 with 2 total loaned books");

        Member member3 = new Member("Member3");
        member3.addLoanedBook(book1);
        result = comparator.compare(member2, member3);
        assertEquals(0, result, "Members with the same number of total loaned books should be equal");
    }
}
