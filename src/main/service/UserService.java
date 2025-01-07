package com.fuelmanagement.service;

import com.fuelmanagement.dao.UserDAO;
import com.fuelmanagement.model.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Registers a new user.
     *
     * @param username The username of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return true if the user is registered successfully, false otherwise.
     */
    public boolean registerUser(String username, String email, String password) {
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid user details");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return userDAO.addUser(user);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return The user object, or null if not found.
     */
    public User getUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }

        return userDAO.getUserByUsername(username);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all registered users.
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to delete.
     * @return true if the user is deleted successfully, false otherwise.
     */
    public boolean deleteUser(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        return userDAO.deleteUser(userId);
    }
}
