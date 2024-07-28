package edu.librarysystem.comparators;

import edu.librarysystem.models.Member;
import java.util.Comparator;

public class MemberBooksLoanedComparator implements Comparator<Member> {
    @Override
    public int compare(Member member1, Member member2) {
        return Integer.compare(member1.getCurrentlyLoanedBooks(), member2.getCurrentlyLoanedBooks());
    }
}
