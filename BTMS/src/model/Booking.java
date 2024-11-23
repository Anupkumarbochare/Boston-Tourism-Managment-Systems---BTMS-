/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author anupbochare
 */
public class Booking {
private int bookingId;
    private int touristId;
    private String bookingType; // "Attraction", "Hotel", "Restaurant", "Transport"
    private int itemId;
    private int quantity;
    private Date bookingDate;
    private String status;
    private double totalPrice;
    private boolean isActive;
    private String notes;
    
    // Constructor
    public Booking(int bookingId, int touristId, String bookingType, int itemId, int quantity) {
        this.bookingId = bookingId;
        this.touristId = touristId;
        this.bookingType = bookingType;
        this.itemId = itemId;
        this.quantity = quantity;
        this.bookingDate = new Date();
        this.status = "Confirmed";
        this.isActive = true;
    }
    
    // Getters
    public int getBookingId() { return bookingId; }
    public int getTouristId() { return touristId; }
    public String getBookingType() { return bookingType; }
    public int getItemId() { return itemId; }
    public int getQuantity() { return quantity; }
    public Date getBookingDate() { return bookingDate; }
    public String getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }
    public boolean isActive() { return isActive; }
    public String getNotes() { return notes; }
    
    // Setters
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public void setTouristId(int touristId) { this.touristId = touristId; }
    public void setBookingType(String bookingType) { this.bookingType = bookingType; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public void setStatus(String status) { this.status = status; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setActive(boolean active) { isActive = active; }
    public void setNotes(String notes) { this.notes = notes; }
    
    // Business Methods
    public void cancelBooking() {
        this.status = "Cancelled";
        this.isActive = false;
    }
    
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
    
    public boolean isValidBooking() {
        return quantity > 0 && isActive && !status.equals("Cancelled");
    }
    
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + bookingId +
                ", type='" + bookingType + '\'' +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }   
}
