package edu.librarysystem.comparators;

import edu.librarysystem.models.Member;
import java.util.Comparator;

public class MemberCreationDateComparator implements Comparator<Member> {
    @Override
    public int compare(Member member1, Member member2) {
        return member1.getCreationDate().compareTo(member2.getCreationDate());
    }
}
