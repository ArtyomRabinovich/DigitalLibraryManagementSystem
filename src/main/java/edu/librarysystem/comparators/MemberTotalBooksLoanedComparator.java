package edu.librarysystem.comparators;

import edu.librarysystem.models.Member;
import java.util.Comparator;

/**
 * The MemberTotalBooksLoanedComparator class implements the Comparator interface and
 * provides a comparison function to compare two members by the total number of books they have loaned.
 */
public class MemberTotalBooksLoanedComparator implements Comparator<Member> {

    /**
     * Compares two members by the total number of books they have loaned.
     *
     * @param member1 the first member to be compared.
     * @param member2 the second member to be compared.
     * @return a negative integer, zero, or a positive integer as the first argument
     *         has fewer than, equal to, or more total loaned books than the second.
     */
    @Override
    public int compare(Member member1, Member member2) {
        return Integer.compare(member1.getTotalBooksLoaned(), member2.getTotalBooksLoaned());
    }
}
