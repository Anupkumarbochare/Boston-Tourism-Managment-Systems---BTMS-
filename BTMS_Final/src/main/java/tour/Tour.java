/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tour;

/**
 *
 * @author anupbochare
 */
public class Tour {
private String tourName;
    private String guide;
    private int capacity;
    private double price;
    private boolean isActive;
    private int availableSpots;
    private String schedule;
    private int tourId;
    
    // Constructor
    public Tour(int tourId, String tourName, String guide, int capacity, double price) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.guide = guide;
        this.capacity = capacity;
        this.price = price;
        this.isActive = true;
        this.availableSpots = capacity;
    }
    
    // Getters
    public int getTourId() { return tourId; }
    public String getTourName() { return tourName; }
    public String getGuide() { return guide; }
    public int getCapacity() { return capacity; }
    public double getPrice() { return price; }
    public boolean isActive() { return isActive; }
    public int getAvailableSpots() { return availableSpots; }
    public String getSchedule() { return schedule; }
    
    // Setters
    public void setTourId(int tourId) { this.tourId = tourId; }
    public void setTourName(String tourName) { this.tourName = tourName; }
    public void setGuide(String guide) { this.guide = guide; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setPrice(double price) { this.price = price; }
    public void setActive(boolean active) { isActive = active; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    
    // Business Methods
    public boolean bookSpots(int spots) {
        if (availableSpots >= spots && isActive) {
            availableSpots -= spots;
            return true;
        }
        return false;
    }
    
    public boolean cancelSpots(int spots) {
        if (availableSpots + spots <= capacity) {
            availableSpots += spots;
            isActive = true;
            return true;
        }
        return false;
    }
    
    public boolean hasAvailability() {
        return availableSpots > 0 && isActive;
    }
    
    @Override
    public String toString() {
        return "Tour{" +
                "name='" + tourName + '\'' +
                ", guide='" + guide + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", availableSpots=" + availableSpots +
                '}';
    }    
    
}
