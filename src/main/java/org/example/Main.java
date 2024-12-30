package org.example;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        boolean running = true;

        while (running) {
            // Display Menu
            System.out.println("\n--- User Management ---");
            System.out.println("1. Add User");
            System.out.println("2. Get All Users");
            System.out.println("3. Get User by ID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1: // Add User
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    userService.addUser(firstName, lastName, email);
                    System.out.println("User added successfully!");
                    break;

                case 2: // Get All Users
                    List<User> users = userService.getAllUsers();
                    System.out.println("\n--- All Users ---");
                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        users.forEach(System.out::println);
                    }
                    break;

                case 3: // Get User by ID
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    Optional<User> user = userService.getUserById(userId);
                    user.ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("User not found.")
                    );
                    break;

                case 4: // Update User
                    System.out.print("Enter User ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Optional<User> userToUpdate = userService.getUserById(updateId);
                    if (userToUpdate.isPresent()) {
                        System.out.print("Enter new First Name: ");
                        String newFirstName = scanner.nextLine();
                        System.out.print("Enter new Last Name: ");
                        String newLastName = scanner.nextLine();
                        System.out.print("Enter new Email: ");
                        String newEmail = scanner.nextLine();
                        userService.updateUser(updateId, newFirstName, newLastName, newEmail);
                        System.out.println("User updated successfully!");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 5: // Delete User
                    System.out.print("Enter User ID to delete: ");
                    int deleteId = scanner.nextInt();
                    boolean isDeleted = userService.deleteUser(deleteId);
                    if (isDeleted) {
                        System.out.println("User deleted successfully!");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 6: // Exit
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
