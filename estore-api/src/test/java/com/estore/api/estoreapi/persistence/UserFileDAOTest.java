package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the User File DAO class
 * @author Ashlyn King
 * @author SWEN Faculty
 */
@Tag("Persistence-tier")
class UserFileDAOTest {
    UserFileDAO userFileDAO;
    User[] testUsers;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    void setupUSerFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[4];
        testUsers[0] = new User(0, "Smith", 0, List.of());
        testUsers[1] = new User(1, "Bob", 0, List.of());
        testUsers[2] = new User(2, "John", 0, List.of());
        testUsers[3] = new User(3, "Katie", 0, List.of());

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the user array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"), User[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("doesnt_matter.txt",mockObjectMapper);
        
    }

    @Test
    void testGetUsers() throws IOException {
        // Invoke
        User[] users = userFileDAO.getAll();

        // Analyze
        assertEquals(users.length,testUsers.length);
        for (int i = 0; i < testUsers.length;++i)
            assertEquals(users[i],testUsers[i]);
    }

    @Test
    void testFindUsers() throws IOException {
        // Invoke
        User[] users = userFileDAO.findByName("t");

        // Analyze
        assertEquals(2, users.length);
        assertEquals(users[0],testUsers[0]);
        assertEquals(users[1],testUsers[3]);
    }

    @Test
    void testGetUser() throws IOException {
        // Invoke
        User user = userFileDAO.findByID(0);

        // Analzye
        assertEquals(user,testUsers[0]);
    }

    @Test
    void testDelete() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.delete(3),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(true, result);
        // We check the internal tree map size against the length
        // of the test users array - 1 (because of the delete)
        // Because users attribute of UserFileDAO is package private
        // we can access it directly
        assertEquals(userFileDAO.users.size(),testUsers.length-1);
    }

    @Test
    void testCreate() throws IOException {
        // Setup
        User newuser = new User(4, "Person", 0, List.of());

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.create(newuser),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        User actual = userFileDAO.findByID(newuser.getId());
        assertEquals(actual.getId(),newuser.getId());
        assertEquals(actual.getName(), newuser.getName());
    }

    @Test
    void testUpdate() throws IOException {
        // Setup
        User user = new User(3, "Jill", 0, List.of());

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.update(user),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        User actual = userFileDAO.findByID(user.getId());
        assertEquals(actual,user);
    }

    @Test
    void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(User[].class));

        User user = new User(104, "Fire", 0, List.of());

        assertThrows(IOException.class,
                        () -> userFileDAO.create(user),
                        "IOException not thrown");
    }

    @Test
    void testGetUserNotFound() throws IOException {
        // Invoke
        User user = userFileDAO.findByID(98);

        // Analyze
        assertEquals(null, user);
    }

    @Test
    void testDeleteNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.delete(98),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(false, result);
        assertEquals(userFileDAO.users.size(),testUsers.length);
    }
    

    @Test
    void testUpdateUserNotFound() {
        // Setup
        User user = new User(98, "Thunder", 0, List.of());

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.update(user),
                                "Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }

    @Test
    void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the USerFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),User[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new UserFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}
