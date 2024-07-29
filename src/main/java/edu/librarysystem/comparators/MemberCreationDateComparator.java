package edu.librarysystem.comparators;

import edu.librarysystem.models.Member;
import java.util.Comparator;

/**
 * The MemberCreationDateComparator class implements the Comparator interface and
 * provides a comparison function to compare two members by their creation dates.
 */
public class MemberCreationDateComparator implements Comparator<Member> {

    /**
     * Compares two members by their creation dates.
     *
     * @param member1 the first member to be compared.
     * @param member2 the second member to be compared.
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is earlier than, equal to, or later than the second.
     */
    @Override
    public int compare(Member member1, Member member2) {
        return member1.getCreationDate().compareTo(member2.getCreationDate());
    }
}
