/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anupbochare
 */
public class UserCredentialsManager {
    private static UserCredentialsManager instance;

    private Map<String, UserCredentials> userCredentials;
      private Map<String, String> userOrganizations;
    private Map<String, String> userEnterprises;
    
    private UserCredentialsManager() {
        userCredentials = new HashMap<>();
                userOrganizations = new HashMap<>();
        userEnterprises = new HashMap<>();
        initializeDefaultCredentials();
    }
    public static synchronized UserCredentialsManager getInstance() {
        if (instance == null) {
            instance = new UserCredentialsManager();
        }
        return instance;
    }
    private void initializeDefaultCredentials() {
        // Admin users
        addUser("admin", "admin123", "Admin");
        addUser("anup", "anup123", "Admin");
        
        // Tourist users
        addUser("tourist", "tourist123", "Tourist");
        addUser("tourist2", "tourist2123", "Tourist");
        
        // Tour Guide users
        addUser("guide1", "guide123", "TourGuide");
        addUser("guide2", "guide456", "TourGuide");

        // Transport Coordinator users
        addUser("transport1", "trans123", "TransportManager");
        addUser("transport2", "trans456", "TransportManager");

        // Event Manager users
        addUser("event1", "event123", "EventManager");
        addUser("event2", "event456", "EventManager");

        // Hospitality Manager users
        addUser("hotel1", "hotel123", "HospitalityManager");
        addUser("restaurant1", "rest123", "HospitalityManager");

        // Data Analyst users
        addUser("analyst1", "analyst123", "DataAnalyst");
        addUser("market1", "market123", "MarketResearcher");

        // Customer Support users
        addUser("support1", "support123", "CustomerSupport");
    }
public String getOrganizationForUser(String username) {
        return userOrganizations.getOrDefault(username, "Unknown");
    }

    public String getEnterpriseForUser(String username) {
        return userEnterprises.getOrDefault(username, "Unknown");
    }
public boolean validateUser(String username, String password) {
    UserCredentials user = userCredentials.get(username);
    if (user != null) {
        return user.getPassword().equals(password);
    }
    return false;
}

    public String getUserRole(String username) {
        UserCredentials user = userCredentials.get(username);
        return user != null ? user.getRole() : null;
    }

 public void addUser(String username, String password, String role) {
        if (userCredentials.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        UserCredentials newUser = new UserCredentials(username, password, role);
        userCredentials.put(username, newUser);
        // After adding, update the credentials list
        updateCredentialsList(username, password, role);
    }
private void updateCredentialsList(String username, String password, String role) {
    switch(role) {
        case "EventManager":
            System.out.println("Added new EventManager: " + username);
            break;
        case "TourGuide":
            System.out.println("Added new TourGuide: " + username);
            break;
        case "TransportManager":
            System.out.println("Added new TransportManager: " + username);
            break;
        case "HospitalityManager":
            System.out.println("Added new HospitalityManager: " + username);
            break;
        case "Tourist":
            System.out.println("Added new Tourist: " + username);
            break;
        case "Admin":
            System.out.println("Added new Admin: " + username);
            break;
        case "DataAnalyst":
            System.out.println("Added new DataAnalyst: " + username);
            break;
        case "CustomerSupport":
            System.out.println("Added new CustomerSupport: " + username);
            break;
        default:
            System.out.println("Added new user with unknown role: " + username);
            break;
    }
}
    public void updateUser(String oldUsername, String newUsername, String password, String role) {
        if (!userCredentials.containsKey(oldUsername)) {
            throw new IllegalArgumentException("User not found: " + oldUsername);
        }
        if (!oldUsername.equals(newUsername) && userCredentials.containsKey(newUsername)) {
            throw new IllegalArgumentException("New username already exists");
        }
        userCredentials.remove(oldUsername);
        userCredentials.put(newUsername, new UserCredentials(newUsername, password, role));
    }

    public boolean removeUser(String username) {
        return userCredentials.remove(username) != null;
    }

    public boolean updatePassword(String username, String newPassword) {
        UserCredentials user = userCredentials.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean updateRole(String username, String newRole) {
        UserCredentials user = userCredentials.get(username);
        if (user != null) {
            user.setRole(newRole);
            return true;
        }
        return false;
    }

    public boolean userExists(String username) {
        return userCredentials.containsKey(username);
    }

    public static class UserCredentials {
        private String username;
        private String password;
        private String role;

        public UserCredentials(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "UserCredentials{" +
                    "username='" + username + '\'' +
                    ", role='" + role + '\'' +
                    '}';
        }
    }
}
