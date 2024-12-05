/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

/**
 *
 * @author anupbochare
 */
public class Event {
private int eventId;
    private String name;
    private String location;
    private String description;
    private double ticketPrice;
    private String date;
    private String time;
    private int capacity;
        private int initialCapacity;  // Add this field

    private boolean isActive;
    private String category;
    private String organizer;

    public Event(int eventId, String name, String location, String description,
            double ticketPrice, String date, String time, int capacity, boolean isActive,
            String category, String organizer) {
        this.eventId = eventId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
                this.initialCapacity = capacity;  // Store initial capacity

        this.isActive = isActive;
        this.category = category;
        this.organizer = organizer;
    }

    // Getters
    public int getEventId() { return eventId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public double getTicketPrice() { return ticketPrice; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getCapacity() { return capacity; }
    public boolean isActive() { return isActive; }
    public String getCategory() { return category; }
    public String getOrganizer() { return organizer; }

    // Setters
    public void setEventId(int eventId) { this.eventId = eventId; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setTicketPrice(double ticketPrice) { this.ticketPrice = ticketPrice; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setActive(boolean active) { isActive = active; }
    public void setCategory(String category) { this.category = category; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }  
    public int getInitialCapacity() {
        return initialCapacity;
    }
}
