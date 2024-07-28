package edu.librarysystem.comparators;

import edu.librarysystem.models.Member;
import java.util.Comparator;

public class MemberTotalBooksLoanedComparator implements Comparator<Member> {
    @Override
    public int compare(Member member1, Member member2) {
        return Integer.compare(member1.getTotalBooksLoaned(), member2.getTotalBooksLoaned());
    }
}
