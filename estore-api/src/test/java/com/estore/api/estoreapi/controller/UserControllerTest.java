package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.KeyboardFileDAO;
import com.estore.api.estoreapi.persistence.UserFileDAO;
import com.estore.api.estoreapi.model.Keyboard;
import com.estore.api.estoreapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class UserControllerTest {
    private UserController userController;
    private UserFileDAO mockUserFileDao;
    private KeyboardFileDAO mockKeyboardFileDAO;

    @BeforeEach
    public void setupUserController(){
        mockUserFileDao = mock(UserFileDAO.class);
        mockKeyboardFileDAO = mock(KeyboardFileDAO.class);
        userController = new UserController(mockUserFileDao, mockKeyboardFileDAO);
    }

    @Test
    public void testGetUser() throws IOException{
        User testUser = new User(0, "Issac", 0);
        when(mockUserFileDao.findByID(testUser.getId())).thenReturn(testUser);

        ResponseEntity<User> response = userController.getUser(testUser.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testGetUserNotFound() throws IOException{
        int userID = 1;

        when(mockUserFileDao.findByID(userID)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(userID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetUserHandleExceptions() throws Exception{
        int userID = 1;

        doThrow(new IOException()).when(mockUserFileDao).findByID(userID);

        ResponseEntity<User> response = userController.getUser(userID);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateUser() throws IOException{
        User testUser = new User(0, "Issac", 0);
        when(mockUserFileDao.findByName("Issac")).thenReturn(new User[0]);
        when(mockUserFileDao.create(testUser)).thenReturn(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testCreateUserFailed() throws IOException{
        User testUser = new User(0, "Issac", 0);
        when(mockUserFileDao.findByName("Issac")).thenReturn(new User[] { testUser });
        when(mockUserFileDao.create(testUser)).thenReturn(null);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException{
        User testUser = new User(0, "Issac", 0);
        when(mockUserFileDao.findByName("Issac")).thenReturn(new User[0]);
        doThrow(new IOException()).when(mockUserFileDao).create(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAddAdminCartFails() throws IOException {
        User adminUser = new User(0, "Issac", 0);
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        when(mockUserFileDao.findByID(0)).thenReturn(adminUser);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(adminUser.getId(), keyboard.getId());
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testAddUserCartSucceeds() throws IOException {
        User user = new User(0, "Issac", 1);
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), keyboard.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddUserCartUserNotFound() throws IOException {
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        when(mockUserFileDao.findByID(0)).thenReturn(null);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(0, keyboard.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddUserCartKeyboardNotFound() throws IOException {
        User user = new User(0, "Issac", 1);
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(null);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), 0);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddUserCartThrowsException() throws IOException {
        User user = new User(0, "Issac", 1);
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);
        doThrow(new IOException()).when(mockUserFileDao).findByID(0);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), keyboard.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testRemoveAdminCartFails() throws IOException {
        User adminUser = new User(0, "Issac", 0);
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        when(mockUserFileDao.findByID(0)).thenReturn(adminUser);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(adminUser.getId(), keyboard.getId());
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testRemoveUserCartSucceeds() throws IOException {
        User user = new User(0, "Issac", 1);
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        user.addToCart(keyboard);
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), keyboard.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRemoveUserCartUserNotFound() throws IOException {
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        when(mockUserFileDao.findByID(0)).thenReturn(null);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(0, keyboard.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRemoveUserCartKeyboardNotFound() throws IOException {
        User user = new User(0, "Issac", 1);
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(null);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), 0);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRemoveUserCartThrowsException() throws IOException {
        User user = new User(0, "Issac", 1);
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, 10);
        user.addToCart(keyboard);
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);
        doThrow(new IOException()).when(mockUserFileDao).findByID(0);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), keyboard.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testUpdateUser() throws IOException{
        User testUser = new User(0, "Issac", 0);
        
        when(mockUserFileDao.findByID(0)).thenReturn(testUser);
        when(mockUserFileDao.update(testUser)).thenReturn(testUser);
        testUser.setName("Chan");

        ResponseEntity<User> response = userController.updateUser(testUser);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testUpdateUserFail() throws IOException{
        User testUser = new User(0, "Issac", 0);

        when(mockUserFileDao.update(testUser)).thenReturn(null);

        ResponseEntity<User> response = userController.updateUser(testUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateUserHandleException() throws IOException{
        User testUser = new User(0, "Issac", 0);

        when(mockUserFileDao.findByID(0)).thenReturn(testUser);
        doThrow(new IOException()).when(mockUserFileDao).update(testUser);

        ResponseEntity<User> response = userController.updateUser(testUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetUsers() throws IOException{
        User[] testUsers = new User[2];
        testUsers[0] = new User(0, "Issac", 0);
        testUsers[1] = new User(1, "Poopyface", 1);

        when(mockUserFileDao.getAll()).thenReturn(testUsers);

        ResponseEntity<User[]> response = userController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUsers, response.getBody());
    }

    @Test
    public void testGetUsersHandleException() throws IOException{
        doThrow(new IOException()).when(mockUserFileDao).getAll();

        ResponseEntity<User[]> response = userController.getUsers();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchUser() throws IOException{
        String searchString = "Is";
        User[] testUsers = new User[2];
        testUsers[0] = new User(0, "Issac", 0);
        testUsers[1] = new User(1, "Maya", 0);

        when(mockUserFileDao.findByName(searchString)).thenReturn(testUsers);

        ResponseEntity<User[]> response = userController.searchUsers(searchString);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUsers, response.getBody());
    }

    @Test
    public void testSearchUserHandleException() throws IOException{
        String searchString = "Is";
        
        doThrow(new IOException()).when(mockUserFileDao).findByName(searchString);

        ResponseEntity<User[]> response = userController.searchUsers(searchString);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteUser() throws IOException{
        int userID = 0;
        User testUser = new User(userID, "Issac", 0);

        when(mockUserFileDao.findByID(userID)).thenReturn(testUser);
        when(mockUserFileDao.delete(userID)).thenReturn(true);

        ResponseEntity<User> response = userController.deleteUser(userID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteUserFail() throws IOException{
        int userID = 0;

        when(mockUserFileDao.delete(userID)).thenReturn(false);

        ResponseEntity<User> response = userController.deleteUser(userID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteUserHandleException() throws IOException{
        int userID = 0;
        User testUser = new User(userID, "Issac", 0);

        when(mockUserFileDao.findByID(userID)).thenReturn(testUser);
        doThrow(new IOException()).when(mockUserFileDao).delete(userID);

        ResponseEntity<User> response = userController.deleteUser(userID);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
