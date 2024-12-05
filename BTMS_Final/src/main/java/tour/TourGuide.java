/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tour;

/**
 *
 * @author anupbochare
 */
public class TourGuide {

       private int guideId;
    private String name;
    private String username;
    private String password;
    private String contactInfo;
    private boolean active;

    public TourGuide(int guideId, String name, String username, String password, 
            String contactInfo, boolean active) {
        this.guideId = guideId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
        this.active = active;
    }

    // Getters
    public int getGuideId() {
        return guideId;
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

    public String getContactInfo() {
        return contactInfo;
    }

    public boolean isActive() {
        return active;
    }

    // Setters
    public void setGuideId(int guideId) {
        this.guideId = guideId;
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

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TourGuide{" +
                "guideId=" + guideId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", active=" + active +
                '}';
    }
}
