/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tourist;

import Booking.Booking;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author anupbochare
 */
public class Tourist {

    private int touristId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String nationality;
    private String passportNumber;
    private String preferences;
    private boolean isActive;
    private int eventsBooked;
    private int attractionsBooked;
    private List<Booking> bookings;

    public Tourist(int touristId, String name, String username, String password, 
        String email, String phone, String nationality, String passportNumber, 
        String preferences, boolean isActive, int eventsBooked, int attractionsBooked) {
    this.touristId = touristId;
    this.name = name;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.nationality = nationality;
    this.passportNumber = passportNumber;
    this.preferences = preferences;
    this.isActive = isActive;
    this.eventsBooked = eventsBooked;
    this.attractionsBooked = attractionsBooked;
    this.bookings = new ArrayList<>();
}

    // Getters and Setters
    public int getTouristId() { return touristId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getNationality() { return nationality; }
    public String getPassportNumber() { return passportNumber; }
    public String getPreferences() { return preferences; }
    public boolean isActive() { return isActive; }
    public List<Booking> getBookings() { return bookings; }

    public void setTouristId(int touristId) { this.touristId = touristId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
    public void setActive(boolean active) { isActive = active; }
 public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getEventsBooked() { return eventsBooked; }
    public int getAttractionsBooked() { return attractionsBooked; }

    // Add these new setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void incrementEventsBooked() { this.eventsBooked++; }
    public void incrementAttractionsBooked() { this.attractionsBooked++; }
}
