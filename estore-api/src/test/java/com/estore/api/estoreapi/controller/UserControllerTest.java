package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.Key;

import com.estore.api.estoreapi.persistence.GenericDAO;
import com.estore.api.estoreapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-Tier")
public class UserControllerTest {
    private UserController userController;
    private GenericDAO mockGenericDao;

    @BeforeEach
    public void setupUserController(){
        mockGenericDao = mock(GenericDAO.class);
        userController = new UserController(mockGenericDao);
    }

    @Test
    public void testGetUser() throws IOException{
        User testUser = new User(0, "Issac", 0);
        when(mockGenericDao.findByID(testUser.getId())).thenReturn(testUser);

        ResponseEntity<User> response = userController.getUser(testUser.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testGetUserNotFound() throws IOException{
        int userID = 1;

        when(mockGenericDao.findByID(userID)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(userID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetUserHandleExceptions() throws Exception{
        int userID = 1;

        doThrow(new IOException()).when(mockGenericDao).findByID(userID);

        ResponseEntity<User> response = userController.getUser(userID);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateUser() throws IOException{
        User testUser = new User(0, "Issac", 0);

        when(mockGenericDao.create(testUser)).thenReturn(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testCreateUserFailed() throws IOException{
        User testUser = new User(0, "Issac", 0);

        when(mockGenericDao.create(testUser)).thenReturn(null);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException{
        User testUser = new User(0, "Issac", 0);

        doThrow(new IOException()).when(mockGenericDao).create(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testUpdateUser() throws IOException{
        User testUser = new User(0, "Issac", 0);
        
        when(mockGenericDao.update(testUser)).thenReturn(testUser);
        ResponseEntity<User> response = userController.updateUser(testUser);
        testUser.setName("Chan");

        response = userController.updateUser(testUser);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }
}
