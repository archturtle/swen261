package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import com.estore.api.estoreapi.persistence.KeyboardFileDAO;
import com.estore.api.estoreapi.persistence.UserFileDAO;
import com.estore.api.estoreapi.model.CartItem;
import com.estore.api.estoreapi.model.CustomKeyboard;
import com.estore.api.estoreapi.model.Keyboard;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.model.CartItem.Type;
import com.estore.api.estoreapi.model.CustomKeyboard.Size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
class UserControllerTest {
    private UserController userController;
    private UserFileDAO mockUserFileDao;
    private KeyboardFileDAO mockKeyboardFileDAO;

    @BeforeEach
    void setupUserController(){
        mockUserFileDao = mock(UserFileDAO.class);
        mockKeyboardFileDAO = mock(KeyboardFileDAO.class);
        userController = new UserController(mockUserFileDao, mockKeyboardFileDAO);
    }

    @Test
    void testGetUser() throws IOException{
        User testUser = new User(0, "Issac", 0, List.of());
        when(mockUserFileDao.findByID(testUser.getId())).thenReturn(testUser);

        ResponseEntity<User> response = userController.getUser(testUser.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void testGetUserNotFound() throws IOException{
        int userID = 1;

        when(mockUserFileDao.findByID(userID)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(userID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetUserHandleExceptions() throws Exception{
        int userID = 1;

        doThrow(new IOException()).when(mockUserFileDao).findByID(userID);

        ResponseEntity<User> response = userController.getUser(userID);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testCreateUser() throws IOException{
        User testUser = new User(0, "Issac", 0, List.of());
        when(mockUserFileDao.findByName("Issac")).thenReturn(new User[0]);
        when(mockUserFileDao.create(testUser)).thenReturn(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void testCreateUserFailed() throws IOException{
        User testUser = new User(0, "Issac", 0, List.of());
        when(mockUserFileDao.findByName("Issac")).thenReturn(new User[] { testUser });
        when(mockUserFileDao.create(testUser)).thenReturn(null);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void testCreateUserHandleException() throws IOException{
        User testUser = new User(0, "Issac", 0, List.of());
        when(mockUserFileDao.findByName("Issac")).thenReturn(new User[0]);
        doThrow(new IOException()).when(mockUserFileDao).create(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    void testAddUserCartUserNotFound() throws IOException {
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, 0, null);
        when(mockUserFileDao.findByID(0)).thenReturn(null);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(0, cartItem);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddAdminCartFails() throws IOException {
        User adminUser = new User(0, "Issac", 0, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);
        when(mockUserFileDao.findByID(0)).thenReturn(adminUser);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(adminUser.getId(), cartItem);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void testAddUserCartKeyboardNotFound() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(null);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddUserCartFailsToLowQuantity() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 0, keyboard.getId(), null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, response.getStatusCode());
    }

    @Test
    void testAddUserCartFailsToHighQuantity() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 20, keyboard.getId(), null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, response.getStatusCode());
    }

    @Test
    void testAddUserCartFailsNotEnoughAvailable() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        CartItem cartItemTwo = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        user.setCart(List.of(cartItem));
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItemTwo);
        assertEquals(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, response.getStatusCode());
    }

    @Test
    void testAddUserCartFailsCustomKeyboardNull() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        CartItem cartItem = new CartItem(Type.CUSTOM_KEYBOARD, 20, -1, null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddUserCartFailsCustomKeyboardQuantityNot1() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        CustomKeyboard customKeyboard = new CustomKeyboard(Size.ONE_HUNDRED, 119.99, "#ff0000", "#00ff00", "#0000ff");
        CartItem cartItem = new CartItem(Type.CUSTOM_KEYBOARD, 20, -1, customKeyboard);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddUserCartThrowsException() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenThrow(IOException.class);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testAddUserCartSucceedsItemDoesntExistInCart() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        Keyboard keyboardOne = new Keyboard(1, "GMMK PRO", 199.99, "It's a keyboard again", 10);
        CartItem cartItemOne = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboardOne.getId(), null);        
        user.setCart(List.of(cartItemOne));

        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddUserCartSucceedsItemExistInCart() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        CartItem cartItemOne = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);        
        user.setCart(List.of(cartItemOne));

        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddUserCartSucceedsItemIsCustomKeyboard() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        CustomKeyboard customKeyboard = new CustomKeyboard(Size.ONE_HUNDRED, 119.99, "#ff0000", "#00ff00", "#0000ff");
        CartItem cartItem = new CartItem(Type.CUSTOM_KEYBOARD, 1, -1, customKeyboard);    

        when(mockUserFileDao.findByID(0)).thenReturn(user);

        ResponseEntity<User> response = userController.addItemToCart(user.getId(), cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    

    

    @Test
    void testRemoveUserCartUserNotFound() throws IOException {
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, 0, null);
        when(mockUserFileDao.findByID(0)).thenReturn(null);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(0, cartItem);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testRemoveAdminCartFails() throws IOException {
        User adminUser = new User(0, "Issac", 0, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);
        when(mockUserFileDao.findByID(0)).thenReturn(adminUser);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(adminUser.getId(), cartItem);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartKeyboardNotFound() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(null);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartFailsToLowQuantity() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 0, keyboard.getId(), null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartFailsCustomKeyboardNull() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        CartItem cartItem = new CartItem(Type.CUSTOM_KEYBOARD, 20, -1, null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartFailsCustomKeyboardQuantityNot1() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        CustomKeyboard customKeyboard = new CustomKeyboard(Size.ONE_HUNDRED, 119.99, "#ff0000", "#00ff00", "#0000ff");
        CartItem cartItem = new CartItem(Type.CUSTOM_KEYBOARD, 20, -1, customKeyboard);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartThrowsException() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenThrow(IOException.class);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartSucceedsItemDoesntExistInCart() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        Keyboard keyboardOne = new Keyboard(1, "GMMK PRO", 199.99, "It's a keyboard again", 10);
        CartItem cartItemOne = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboardOne.getId(), null);        
        user.setCart(List.of(cartItemOne));

        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartSucceedsItemExistInCart() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);        
        CartItem cartItemOne = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);        
        user.setCart(List.of(cartItemOne));

        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartSucceedsRemovalQuantityMoreThanCurrent() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        CartItem cartItemOne = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);        
        user.setCart(List.of(cartItemOne));

        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartSucceedsRemovalQuantityLessThanCurrent() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        Keyboard keyboard = new Keyboard(0, "GMMK 2", 159.99, "It's a keyboard", 10);
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 8, keyboard.getId(), null);        
        CartItem cartItemOne = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboard.getId(), null);        
        user.setCart(List.of(cartItem));

        when(mockUserFileDao.findByID(0)).thenReturn(user);
        when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItemOne);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRemoveUserCartSucceedsItemIsCustomKeyboard() throws IOException {
        User user = new User(0, "Issac", 1, List.of());
        CustomKeyboard customKeyboard = new CustomKeyboard(Size.ONE_HUNDRED, 119.99, "#ff0000", "#00ff00", "#0000ff");
        CartItem cartItem = new CartItem(Type.CUSTOM_KEYBOARD, 1, -1, customKeyboard);    

        when(mockUserFileDao.findByID(0)).thenReturn(user);

        ResponseEntity<User> response = userController.removeItemFromCart(user.getId(), cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateUser() throws IOException{
        User testUser = new User(0, "Issac", 0, List.of());
        
        when(mockUserFileDao.findByID(0)).thenReturn(testUser);
        when(mockUserFileDao.update(testUser)).thenReturn(testUser);
        testUser.setName("Chan");

        ResponseEntity<User> response = userController.updateUser(testUser);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void testUpdateUserFail() throws IOException{
        User testUser = new User(0, "Issac", 0, List.of());

        when(mockUserFileDao.update(testUser)).thenReturn(null);

        ResponseEntity<User> response = userController.updateUser(testUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateUserHandleException() throws IOException{
        User testUser = new User(0, "Issac", 0, List.of());

        when(mockUserFileDao.findByID(0)).thenReturn(testUser);
        doThrow(new IOException()).when(mockUserFileDao).update(testUser);

        ResponseEntity<User> response = userController.updateUser(testUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetUsers() throws IOException{
        User[] testUsers = new User[2];
        testUsers[0] = new User(0, "Issac", 0, List.of());
        testUsers[1] = new User(1, "Poopyface", 1, List.of());

        when(mockUserFileDao.getAll()).thenReturn(testUsers);

        ResponseEntity<User[]> response = userController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUsers, response.getBody());
    }

    @Test
    void testGetUsersHandleException() throws IOException{
        doThrow(new IOException()).when(mockUserFileDao).getAll();

        ResponseEntity<User[]> response = userController.getUsers();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testSearchUser() throws IOException{
        String searchString = "Is";
        User[] testUsers = new User[2];
        testUsers[0] = new User(0, "Issac", 0, List.of());
        testUsers[1] = new User(1, "Maya", 0, List.of());

        when(mockUserFileDao.findByName(searchString)).thenReturn(testUsers);

        ResponseEntity<User[]> response = userController.searchUsers(searchString);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUsers, response.getBody());
    }

    @Test
    void testSearchUserHandleException() throws IOException{
        String searchString = "Is";
        
        doThrow(new IOException()).when(mockUserFileDao).findByName(searchString);

        ResponseEntity<User[]> response = userController.searchUsers(searchString);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testDeleteUser() throws IOException{
        int userID = 0;
        User testUser = new User(userID, "Issac", 0, List.of());

        when(mockUserFileDao.findByID(userID)).thenReturn(testUser);
        when(mockUserFileDao.delete(userID)).thenReturn(true);

        ResponseEntity<User> response = userController.deleteUser(userID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteUserFail() throws IOException{
        int userID = 0;

        when(mockUserFileDao.delete(userID)).thenReturn(false);

        ResponseEntity<User> response = userController.deleteUser(userID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteUserHandleException() throws IOException{
        int userID = 0;
        User testUser = new User(userID, "Issac", 0, List.of());

        when(mockUserFileDao.findByID(userID)).thenReturn(testUser);
        doThrow(new IOException()).when(mockUserFileDao).delete(userID);

        ResponseEntity<User> response = userController.deleteUser(userID);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
