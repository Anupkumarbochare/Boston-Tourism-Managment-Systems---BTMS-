/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
    private String email;
    private String phone;
    private String nationality;
    private List<Booking> bookings;
    private String address;
    private String passportNumber;
    private Date registrationDate;
    private String preferences;
    private boolean isActive;

    // Constructor
    public Tourist(int touristId, String name, String email, String phone, String nationality) {
        this.touristId = touristId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nationality = nationality;
        this.bookings = new ArrayList<>();
        this.registrationDate = new Date();
        this.isActive = true;
    }

    // Getters
    public int getTouristId() {
        return touristId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getNationality() {
        return nationality;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public String getAddress() {
        return address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getPreferences() {
        return preferences;
    }

    public boolean isActive() {
        return isActive;
    }

    // Setters
    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Business Methods
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public List<Booking> getActiveBookings() {
        return bookings.stream()
                .filter(booking -> booking.isActive())
                .collect(Collectors.toList());
    }

    public boolean hasActiveBookings() {
        return bookings.stream()
                .anyMatch(booking -> booking.isActive());
    }

    @Override
    public String toString() {
        return "Tourist{"
                + "id=" + touristId
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", nationality='" + nationality + '\''
                + ", activeBookings=" + getActiveBookings().size()
                + '}';
    }
}
