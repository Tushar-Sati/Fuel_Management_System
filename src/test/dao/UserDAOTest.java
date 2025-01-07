package dao;

import model.User;
import org.junit.jupiter.api.*;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDAOTest {

    private UserDAO userDAO;

    @BeforeAll
    public void setup() {
        userDAO = new UserDAO();
    }

    @BeforeEach
    public void clearDatabase() {
        // Clear the Users table before each test
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Users")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");

        userDAO.addUser(user);

        List<User> users = userDAO.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUsername());
        assertEquals("testuser@example.com", users.get(0).getEmail());
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("securepassword");

        userDAO.addUser(user);

        User retrievedUser = userDAO.getUserByUsername("john_doe");
        assertNotNull(retrievedUser);
        assertEquals("john_doe", retrievedUser.getUsername());
        assertEquals("john@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("alice");
        user1.setEmail("alice@example.com");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setUsername("bob");
        user2.setEmail("bob@example.com");
        user2.setPassword("password2");

        userDAO.addUser(user1);
        userDAO.addUser(user2);

        List<User> users = userDAO.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("delete_me");
        user.setEmail("delete@example.com");
        user.setPassword("deletepassword");

        userDAO.addUser(user);

        List<User> users = userDAO.getAllUsers();
        int userId = users.get(0).getUserId();

        userDAO.deleteUser(userId);

        users = userDAO.getAllUsers();
        assertTrue(users.isEmpty());
    }

    @AfterAll
    public void tearDown() {
        // Cleanup database after all tests
        clearDatabase();
    }
}
