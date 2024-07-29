package edu.librarysystem.comparators;

import edu.librarysystem.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MemberCreationDateComparatorTest {

    private MemberCreationDateComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new MemberCreationDateComparator();
    }

    @Test
    public void testCompare() throws InterruptedException {
        Member member1 = new Member("Member1");
        Thread.sleep(10); // Ensure there's a time difference
        Member member2 = new Member("Member2");

        int result = comparator.compare(member1, member2);
        assertTrue(result < 0, "Member1 created earlier should be less than Member2 created later");

        result = comparator.compare(member2, member1);
        assertTrue(result > 0, "Member2 created later should be greater than Member1 created earlier");

        Member member3 = new Member("Member3");
        result = comparator.compare(member3, member3);
        assertEquals(0, result, "Comparing the same member should result in equality");
    }
}
