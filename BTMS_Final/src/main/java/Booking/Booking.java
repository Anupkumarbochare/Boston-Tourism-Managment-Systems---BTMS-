/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Booking;

import java.util.Date;

/**
 *
 * @author anupbochare
 */
public class Booking {
 private int bookingId;
    private String touristId;
    private int itemId;  // attraction or event id
    private String itemType; // "ATTRACTION" or "EVENT"
    private String date;
    private int quantity;
    private boolean isActive;
    
    public Booking(int bookingId, String touristId, int itemId, String itemType, 
            String date, int quantity, boolean isActive) {
        this.bookingId = bookingId;
        this.touristId = touristId;
        this.itemId = itemId;
        this.itemType = itemType;
        this.date = date;
        this.quantity = quantity;
        this.isActive = isActive;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
   
    
    
}
