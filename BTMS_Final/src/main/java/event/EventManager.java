/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

/**
 *
 * @author anupbochare
 */
public class EventManager {
private int managerId;
    private String name;
    private String username;
    private String password;
    private String contactInfo;
    private String specialization;
    private boolean isActive;
    
    public EventManager(int managerId, String name, String username, String password, 
            String contactInfo, String specialization, boolean isActive) {
        this.managerId = managerId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
        this.specialization = specialization;
        this.isActive = isActive;
    }    

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
