package edu.librarysystem.services;

import edu.librarysystem.models.Member;
import edu.librarysystem.singleton.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@code UserService}.
 */
public class UserServiceTest {

    private Library library;
    private UserService service;

    @BeforeEach
    public void setUp() throws Exception {
        library = Mockito.mock(Library.class);
        service = new UserService();

        // Use reflection to inject the mocked library into the service
        Field libraryField = UserService.class.getDeclaredField("library");
        libraryField.setAccessible(true);
        libraryField.set(service, library);
    }

    /**
     * Tests the {@code addMember} method of {@code UserService}.
     * Verifies that the {@code addMember} method of {@code Library} is called with the correct parameter.
     */
    @Test
    public void testAddMember() {
        service.addMember("Test Member");
        Mockito.verify(library).addMember("Test Member");
    }

    /**
     * Tests the {@code deleteMember} method of {@code UserService}.
     * Verifies that the {@code deleteMember} method of {@code Library} is called with the correct parameter.
     */
    @Test
    public void testDeleteMember() {
        service.deleteMember(1);
        Mockito.verify(library).deleteMember(1);
    }

    /**
     * Tests the {@code getMember} method of {@code UserService}.
     * Verifies that the {@code getMember} method of {@code Library} is called with the correct parameter.
     * Also verifies that the returned member is the expected one.
     */
    @Test
    public void testGetMember() {
        Member member = new Member("Test Member");
        Mockito.when(library.getMember(1)).thenReturn(member);
        Member retrievedMember = service.getMember(1);
        Mockito.verify(library).getMember(1);
        assert retrievedMember.equals(member);
    }

    /**
     * Tests the {@code getAllMembers} method of {@code UserService}.
     * Verifies that the {@code getAllMembers} method of {@code Library} is called.
     * Also verifies that the returned list of members is the expected one.
     */
    @Test
    public void testGetAllMembers() {
        List<Member> members = Arrays.asList(new Member("Test Member 1"), new Member("Test Member 2"));
        Mockito.when(library.getAllMembers()).thenReturn(members);
        List<Member> retrievedMembers = service.getAllMembers();
        Mockito.verify(library).getAllMembers();
        assert retrievedMembers.equals(members);
    }
}
