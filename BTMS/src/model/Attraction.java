/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anupbochare
 */
public class Attraction {

    private int attractionId;
    private String name;
    private String location;
    private String description;
    private double ticketPrice;
    private String hours;
    private int capacity;
    private boolean isActive;
    private String category;
    private String imageUrl;
    private int currentVisitors;
    private String contactInfo;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }


    // Constructor
public Attraction(int attractionId, String name, String location, String description,
            double ticketPrice, String openingHours, int capacity, boolean isActive) {
        this.attractionId = attractionId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.hours = openingHours;  // Fixed: was using hours instead of openingHours
        this.capacity = capacity;
        this.isActive = isActive;
        this.currentVisitors = 0;
    }

    // Getters
    public int getAttractionId() {
        return attractionId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getCurrentVisitors() {
        return currentVisitors;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // Setters
    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCurrentVisitors(int currentVisitors) {
        this.currentVisitors = currentVisitors;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Business Methods
    public boolean hasAvailability() {
        return currentVisitors < capacity;
    }

    public boolean addVisitor() {
        if (hasAvailability()) {
            currentVisitors++;
            return true;
        }
        return false;
    }

    public boolean removeVisitor() {
        if (currentVisitors > 0) {
            currentVisitors--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Attraction{"
                + "name='" + name + '\''
                + ", location='" + location + '\''
                + ", ticketPrice=" + ticketPrice
                + ", openingHours='" + hours + '\''
                + '}';
    }
}
