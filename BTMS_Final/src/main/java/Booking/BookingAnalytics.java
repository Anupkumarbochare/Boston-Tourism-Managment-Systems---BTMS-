/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Booking;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author anupbochare
 */
public class BookingAnalytics {
 private Map<String, Integer> attractionBookings = new HashMap<>();
    private Map<String, Double> attractionRevenue = new HashMap<>();
    private Map<LocalDateTime, Integer> bookingTimestamps = new HashMap<>();
    private Map<String, Integer> eventBookings = new HashMap<>();
    private Map<String, Double> eventRevenue = new HashMap<>();

    // Track attraction booking
    public void trackAttractionBooking(String attractionName, double price) {
        attractionBookings.merge(attractionName, 1, Integer::sum);
        attractionRevenue.merge(attractionName, price, Double::sum);
        bookingTimestamps.put(LocalDateTime.now(), 1);
    }

    // Track event booking
    public void trackEventBooking(String eventName, String category, double price) {
        eventBookings.merge(eventName, 1, Integer::sum);
        eventRevenue.merge(eventName, price, Double::sum);
    }

    // Get analytics methods
    public Map<String, Integer> getPopularAttractions() {
        return attractionBookings.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
    }

    public Map<LocalDateTime, Integer> getPeakBookingTimes() {
        return bookingTimestamps;
    }

    public double getAverageCapacityUtilization(String attractionName, int totalCapacity) {
        return (attractionBookings.getOrDefault(attractionName, 0) * 100.0) / totalCapacity;
    }

    public double getRevenueForAttraction(String attractionName) {
        return attractionRevenue.getOrDefault(attractionName, 0.0);
    }
   
}
