package service;

import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");

        when(userDAO.addUser(user)).thenReturn(true);

        boolean result = userService.registerUser("testuser", "testuser@example.com", "password123");
        assertTrue(result);

        verify(userDAO, times(1)).addUser(any(User.class));
    }

    @Test
    public void testRegisterUser_InvalidInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser("", "", "");
        });

        assertEquals("Invalid user details", exception.getMessage());
        verify(userDAO, never()).addUser(any(User.class));
    }

    @Test
    public void testGetUserByUsername_Success() {
        User mockUser = new User();
        mockUser.setUsername("john_doe");
        mockUser.setEmail("john@example.com");
        mockUser.setPassword("securepassword");

        when(userDAO.getUserByUsername("john_doe")).thenReturn(mockUser);

        User user = userService.getUserByUsername("john_doe");
        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());

        verify(userDAO, times(1)).getUserByUsername("john_doe");
    }

    @Test
    public void testGetUserByUsername_InvalidUsername() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserByUsername("");
        });

        assertEquals("Invalid username", exception.getMessage());
        verify(userDAO, never()).getUserByUsername(anyString());
    }

    @Test
    public void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User(1, "alice", "alice@example.com", "password1"));
        mockUsers.add(new User(2, "bob", "bob@example.com", "password2"));

        when(userDAO.getAllUsers()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());

        verify(userDAO, times(1)).getAllUsers();
    }

    @Test
    public void testDeleteUser_Success() {
        when(userDAO.deleteUser(1)).thenReturn(true);

        boolean result = userService.deleteUser(1);
        assertTrue(result);

        verify(userDAO, times(1)).deleteUser(1);
    }

    @Test
    public void testDeleteUser_InvalidUserId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(-1);
        });

        assertEquals("Invalid user ID", exception.getMessage());
        verify(userDAO, never()).deleteUser(anyInt());
    }
}
