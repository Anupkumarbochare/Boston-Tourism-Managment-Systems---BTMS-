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
public class Restaurant {

    private int restaurantId;
    private String name;
    private String cuisine;
    private String location;
    private int capacity;
    private String operatingHours;
    private double averageCost;
    private boolean isOpen;
    private int availableTables;
    private String contactInfo;
    private String menu;
    private int rating;
    private String description;
    private List<String> specialDiets;

    // Constructor
    public Restaurant(int restaurantId, String name, String cuisine, String location,
            int capacity, String operatingHours, double averageCost, boolean isOpen) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisine = cuisine;
        this.location = location;
        this.capacity = capacity;
        this.operatingHours = operatingHours;
        this.averageCost = averageCost;
        this.isOpen = isOpen;
        this.availableTables = capacity;
        this.specialDiets = new ArrayList<>();
    }

    // Getters
    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getAvailableTables() {
        return availableTables;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getMenu() {
        return menu;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSpecialDiets() {
        return specialDiets;
    }

    // Setters
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setAvailableTables(int availableTables) {
        this.availableTables = availableTables;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Business Methods
    public boolean bookTable() {
        if (availableTables > 0 && isOpen) {
            availableTables--;
            return true;
        }
        return false;
    }

    public boolean cancelBooking() {
        if (availableTables < capacity) {
            availableTables++;
            return true;
        }
        return false;
    }

    public void addSpecialDiet(String diet) {
        specialDiets.add(diet);
    }

    public void removeSpecialDiet(String diet) {
        specialDiets.remove(diet);
    }

    public boolean hasAvailability() {
        return availableTables > 0 && isOpen;
    }

    @Override
    public String toString() {
        return "Restaurant{"
                + "name='" + name + '\''
                + ", cuisine='" + cuisine + '\''
                + ", location='" + location + '\''
                + ", availableTables=" + availableTables
                + ", averageCost=" + averageCost
                + '}';
    }
}
