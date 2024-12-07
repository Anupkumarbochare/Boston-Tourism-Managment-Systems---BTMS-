/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transport;

/**
 *
 * @author anupbochare
 */
public class TransportManager {
     private int managerId;
    private String name;
    private String username;
    private String password;
    private boolean active;

    public TransportManager(int managerId, String name, String username, String password, boolean active) {
        this.managerId = managerId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    // Getters
    public int getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    // Setters
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TransportManager{" +
                "managerId=" + managerId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", active=" + active +
                '}';
    }   
}
