/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anupbochare
 */
public class Hotel {
  private int hotelId;
    private String name;
    private String location;
    private String description;
    private int totalRooms;
    private double pricePerNight;
    private String amenities;
    private int rating;
    private boolean isAvailable;
    private int availableRooms;
    private String contactInfo;
    private String checkInTime;
    private String checkOutTime;
    
    // Constructor
    public Hotel(int hotelId, String name, String location, int totalRooms, 
            double pricePerNight, String amenities, int rating, boolean isAvailable) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.totalRooms = totalRooms;
        this.pricePerNight = pricePerNight;
        this.amenities = amenities;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.availableRooms = totalRooms;
    }
    
    // Getters
    public int getHotelId() { return hotelId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public int getTotalRooms() { return totalRooms; }
    public double getPricePerNight() { return pricePerNight; }
    public String getAmenities() { return amenities; }
    public int getRating() { return rating; }
    public boolean isAvailable() { return isAvailable; }
    public int getAvailableRooms() { return availableRooms; }
    public String getContactInfo() { return contactInfo; }
    public String getCheckInTime() { return checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }
    
    // Setters
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setTotalRooms(int totalRooms) { this.totalRooms = totalRooms; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
    public void setRating(int rating) { this.rating = rating; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }
    
    // Business Methods
    public boolean bookRoom() {
        if (availableRooms > 0 && isAvailable) {
            availableRooms--;
            if (availableRooms == 0) {
                isAvailable = false;
            }
            return true;
        }
        return false;
    }
    
    public boolean cancelBooking() {
        if (availableRooms < totalRooms) {
            availableRooms++;
            isAvailable = true;
            return true;
        }
        return false;
    }
    
    public boolean hasVacancy() {
        return availableRooms > 0 && isAvailable;
    }
    
    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", availableRooms=" + availableRooms +
                '}';
    } 
}
