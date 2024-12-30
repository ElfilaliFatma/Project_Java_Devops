package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private List<User> users = new ArrayList<>();
    private int idCounter = 1;

    // Create User
    public User addUser(String firstName, String lastName, String email) {
        User user = new User(idCounter++, firstName, lastName, email);
        users.add(user);
        return user;
    }

    // Read All Users
    public List<User> getAllUsers() {
        return users;
    }

    // Read User by ID
    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    // Update User
    public Optional<User> updateUser(int id, String firstName, String lastName, String email) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            user.get().setFirstName(firstName);
            user.get().setLastName(lastName);
            user.get().setEmail(email);
        }
        return user;
    }

    // Delete User
    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}
