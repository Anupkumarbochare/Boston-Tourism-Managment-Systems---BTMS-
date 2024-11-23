/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Attraction;
import model.Booking;
import model.Hotel;
import model.Restaurant;
import model.Tour;
import model.TransportationRoute;

/**
 *
 * @author anupbochare
 */
public class BookingService {

 private List<Attraction> attractions;
    private List<Hotel> hotels;
    private List<Restaurant> restaurants;
    private List<TransportationRoute> routes;
    private List<Tour> tours;
    private List<Booking> bookings;

    public BookingService() {
        attractions = new ArrayList<>();
        hotels = new ArrayList<>();
        restaurants = new ArrayList<>();
        routes = new ArrayList<>();
        tours = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeData();
    }

    private void initializeData() {
      // Initialize Attractions with proper time ranges
    attractions.add(new Attraction(1, "Freedom Trail", "Boston",
            "Historic walking trail", 15.0, "9AM-5PM", 100, true));
    attractions.add(new Attraction(2, "Fenway Park", "Boston",
            "Historic baseball park", 25.0, "10AM-6PM", 200, true));
    attractions.add(new Attraction(3, "New England Aquarium", "Boston Waterfront",
            "Marine discovery center featuring penguins and seals", 32.0, "9AM-6PM", 300, true));
    attractions.add(new Attraction(4, "Museum of Fine Arts", "Boston",
            "World-class art museum", 27.0, "10AM-5PM", 250, true));
    attractions.add(new Attraction(5, "Boston Common", "Downtown Boston",
            "America's oldest public park", 0.0, "24 Hours", 1000, true));

        // Initialize Hotels
        hotels.add(new Hotel(1, "Boston Plaza", "Downtown",
                200, 150.0, "WiFi, Pool", 4, true));
        hotels.add(new Hotel(2, "Harbor View", "Waterfront",
                150, 200.0, "Spa, Restaurant", 5, true));

        // Initialize Restaurants
        restaurants.add(new Restaurant(1, "Seafood Haven",
                "Seafood", "Harbor", 50, "11", 45.0, true));
        restaurants.add(new Restaurant(2, "Boston Bistro",
                "American", "Downtown", 40, "10", 35.0, true));

        // Initialize Routes
        routes.add(new TransportationRoute(1, "Harbor Line",
                "Airport", "Downtown", 25.0, "30", 40, true));
        routes.add(new TransportationRoute(2, "City Express",
                "Downtown", "Cambridge", 15.0, "15", 30, true));
                
        // Initialize Tours
        tours.add(new Tour(1, "Freedom Trail Walk", "Guide 1", 20, 25.0));
    tours.add(new Tour(2, "Harbor Cruise", "Guide 2", 30, 35.0));
    tours.add(new Tour(3, "Historic Boston", "Guide 3", 15, 20.0));
    tours.add(new Tour(4, "Food Tasting Tour", "Guide 4", 12, 40.0));    }

    public List<Attraction> getAvailableAttractions() {
        return new ArrayList<>(attractions);
    }

    public List<Tour> getAvailableTours() {
        return new ArrayList<>(tours);
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public void updateAttraction(Attraction attraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getAttractionId() == attraction.getAttractionId()) {
                attractions.set(i, attraction);
                break;
            }
        }
    }

    public void deleteAttraction(int attractionId) {
        attractions.removeIf(a -> a.getAttractionId() == attractionId);
    }

    private int generateBookingId() {
        return bookings.size() + 1;
    }

}

