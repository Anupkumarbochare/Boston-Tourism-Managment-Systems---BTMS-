/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Tourist;
import model.UserCredentialsManager;

/**
 *
 * @author anupbochare
 */
public class UserManagementService {

    private List<Tourist> tourists;
    private UserCredentialsManager credManager;
    private int nextTouristId;

    public UserManagementService() {
        tourists = new ArrayList<>();
        credManager = new UserCredentialsManager();
        nextTouristId = 1;
        initializeSampleTourists();
    }

    private void initializeSampleTourists() {
        // Add sample tourists
        tourists.add(new Tourist(nextTouristId++, "John Doe",
                "john@email.com", "+1234567890", "USA"));
        tourists.add(new Tourist(nextTouristId++, "Jane Smith",
                "jane@email.com", "+1234567891", "UK"));
        tourists.add(new Tourist(nextTouristId++, "Mike Johnson",
                "mike@email.com", "+1234567892", "Canada"));
    }

    // Tourist Management Methods
    public Tourist registerTourist(String name, String email, String phone, String nationality) {
        if (isEmailRegistered(email)) {
            return null;
        }
        Tourist tourist = new Tourist(nextTouristId++, name, email, phone, nationality);
        tourists.add(tourist);
        return tourist;
    }

    public boolean updateTourist(Tourist tourist) {
        for (int i = 0; i < tourists.size(); i++) {
            if (tourists.get(i).getTouristId() == tourist.getTouristId()) {
                tourists.set(i, tourist);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTourist(int touristId) {
        return tourists.removeIf(t -> t.getTouristId() == touristId);
    }

    // Search and Retrieval Methods
    public Tourist getTouristById(int touristId) {
        return tourists.stream()
                .filter(t -> t.getTouristId() == touristId)
                .findFirst()
                .orElse(null);
    }

    public Tourist getTouristByEmail(String email) {
        return tourists.stream()
                .filter(t -> t.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<Tourist> getAllTourists() {
        return new ArrayList<>(tourists);
    }

    // Validation Methods
    private boolean isEmailRegistered(String email) {
        return tourists.stream()
                .anyMatch(t -> t.getEmail().equals(email));
    }

    // User Authentication Methods
    public boolean authenticateUser(String username, String password) {
        return credManager.validateUser(username, password);
    }

    public String getUserRole(String username) {
        return credManager.getUserRole(username);
    }

    // User Credential Management
    public boolean addUserCredentials(String username, String password, String role) {
        return credManager.addUser(username, password, role);
    }

    public boolean updateUserRole(String username, String newRole) {
        return credManager.updateRole(username, newRole);
    }

    public boolean updateUserPassword(String username, String newPassword) {
        return credManager.updatePassword(username, newPassword);
    }
}
