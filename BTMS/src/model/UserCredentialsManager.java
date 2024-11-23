/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anupbochare
 */
public class UserCredentialsManager {

    private List<UserCredentials> userList;

    public UserCredentialsManager() {
        userList = new ArrayList<>();
        initializeUsers();
    }

    private void initializeUsers() {
        // Admin users
        userList.add(new UserCredentials("admin", "admin123", "Admin"));
        userList.add(new UserCredentials("anup", "anup123", "Admin"));

        // Tour Guide users
        userList.add(new UserCredentials("guide1", "guide123", "TourGuide"));
        userList.add(new UserCredentials("guide2", "guide456", "TourGuide"));

        // Transport Coordinator users
        userList.add(new UserCredentials("transport1", "trans123", "TransportCoordinator"));
        userList.add(new UserCredentials("transport2", "trans456", "TransportCoordinator"));

        // Event Manager users
        userList.add(new UserCredentials("event1", "event123", "EventManager"));
        userList.add(new UserCredentials("event2", "event456", "EventManager"));

        // Hospitality Manager users
        userList.add(new UserCredentials("hotel1", "hotel123", "HospitalityManager"));
        userList.add(new UserCredentials("restaurant1", "rest123", "HospitalityManager"));

        // Data Analyst users
        userList.add(new UserCredentials("analyst1", "analyst123", "DataAnalyst"));
        userList.add(new UserCredentials("market1", "market123", "MarketResearcher"));

        // Customer Support users
        userList.add(new UserCredentials("support1", "support123", "CustomerSupport"));
    }

    // Authentication Methods
    public boolean validateUser(String username, String password) {
        return userList.stream()
                .anyMatch(user -> user.getUsername().equals(username)
                && user.getPassword().equals(password));
    }

    public String getUserRole(String username) {
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .map(UserCredentials::getRole)
                .findFirst()
                .orElse(null);
    }

    // User Management Methods
    public boolean addUser(String username, String password, String role) {
        if (userExists(username)) {
            return false;
        }
        return userList.add(new UserCredentials(username, password, role));
    }

    public boolean removeUser(String username) {
        return userList.removeIf(user -> user.getUsername().equals(username));
    }

    public boolean updatePassword(String username, String newPassword) {
        for (UserCredentials user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    public boolean updateRole(String username, String newRole) {
        for (UserCredentials user : userList) {
            if (user.getUsername().equals(username)) {
                user.setRole(newRole);
                return true;
            }
        }
        return false;
    }

    private boolean userExists(String username) {
        return userList.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    // Helper class for user credentials
    private static class UserCredentials {

        private String username;
        private String password;
        private String role;

        public UserCredentials(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        // Getters
        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        // Setters
        public void setPassword(String password) {
            this.password = password;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
