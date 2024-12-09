/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Booking.Booking;
import Transport.TransportManager;
import Transport.TransportationRoute;
import UserFeedback.ServiceFeedback;
import event.Event;
import event.EventManager;
import hospitality.HospitalityProperty;
import hospitality.Hotel;
import hospitality.Restaurant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tour.Attraction;
import tour.Tour;
import tour.TourGuide;
import tourist.Tourist;
import ui.Admin.UserManagementJPanel;
import ui.View.ViewAttractionAndEventPanel;


/**
 *
 * @author anupbochare
 */
public class BookingService {

    private static BookingService instance;

    private List<Attraction> attractions;
    private List<Hotel> hotels;
    private List<Restaurant> restaurants;
    private List<TransportationRoute> routes;
    private List<Tour> tours;
    private List<Booking> bookings;
    private List<TourGuide> guides;
    private List<Event> events;
    private List<Tourist> tourists;
    private List<HospitalityProperty> properties;
    private List<EventManager> eventManagers;
    private List<TransportManager> transportManagers;
    private List<ViewAttractionAndEventPanel> touristPanels = new ArrayList<>();
    private List<ServiceFeedback> serviceFeedbacks;
    private List<UserManagementJPanel> userManagementPanels = new ArrayList<>();

    private BookingService() { // Make constructor private
        attractions = new ArrayList<>();
        hotels = new ArrayList<>();
        restaurants = new ArrayList<>();
        routes = new ArrayList<>();
        tours = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeData();
        guides = new ArrayList<>();
        initializeGuides();
        events = new ArrayList<>();
        initializeEvents();
        tourists = new ArrayList<>();
        initializeTourists();
        properties = new ArrayList<>();
        initializeHospitalityData();
        eventManagers = new ArrayList<>();  // Initialize the list
        initializeEventManagerData();
        transportManagers = new ArrayList<>();
        initializeTransportManagerData();
        serviceFeedbacks = new ArrayList<>();
        initializeServiceFeebackData();
    }

    public static synchronized BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    private void initializeEventManagerData() {
        // Add some demo data if needed
        eventManagers.add(new EventManager(1, "John Doe", "john", "pass123",
                "123-456-7890", "Corporate Events", true));
        eventManagers.add(new EventManager(2, "Jane Smith", "jane", "pass456",
                "234-567-8901", "Wedding Events", true));
    }

    public List<EventManager> getEventManagers() {
        return new ArrayList<>(eventManagers);  // Return a copy of the list
    }

    private void initializeData() {
        // Initialize Attractions with proper time ranges
        attractions.add(new Attraction(1, "Freedom Trail", "Downtown Boston", "Historic walking trail", 15.0, "9AM-5PM", 100, true));
        attractions.add(new Attraction(2, "Fenway Park", "Fenway-Kenmore", "Historic baseball park", 25.0, "10AM-6PM", 200, true));
        attractions.add(new Attraction(3, "New England Aquarium", "Central Wharf", "Marine discovery center", 32.0, "9AM-6PM", 300, true));
        attractions.add(new Attraction(4, "Museum of Fine Arts", "Avenue of the Arts", "Art museum", 27.0, "10AM-5PM", 250, true));
        attractions.add(new Attraction(5, "Boston Common", "Downtown Boston", "Public park", 0.0, "24 Hours", 1000, true));
        attractions.add(new Attraction(6, "Harvard University", "Cambridge", "Historic university", 0.0, "9AM-5PM", 150, true));
        attractions.add(new Attraction(7, "Bunker Hill Monument", "Charlestown", "Historic site", 0.0, "9AM-5PM", 200, true));
        attractions.add(new Attraction(8, "USS Constitution", "Charlestown Navy Yard", "Historic warship", 0.0, "10AM-4PM", 150, true));
        attractions.add(new Attraction(9, "Boston Public Garden", "Downtown Boston", "Public garden", 0.0, "24 Hours", 500, true));
        attractions.add(new Attraction(10, "Quincy Market", "Faneuil Hall", "Historic market", 0.0, "10AM-9PM", 400, true));
        attractions.add(new Attraction(11, "Paul Revere House", "North End", "Historic house", 12.0, "10AM-4:30PM", 100, true));
        attractions.add(new Attraction(12, "Boston Tea Party Ships", "Congress Street Bridge", "Historic site", 29.95, "10AM-5PM", 200, true));
        attractions.add(new Attraction(13, "Isabella Stewart Gardner Museum", "Fenway", "Art museum", 20.0, "11AM-5PM", 150, true));
        attractions.add(new Attraction(14, "Boston Public Library", "Copley Square", "Historic library", 0.0, "9AM-9PM", 300, true));
        attractions.add(new Attraction(15, "Franklin Park Zoo", "Dorchester", "Zoo", 21.95, "10AM-4PM", 400, true));
        attractions.add(new Attraction(16, "Boston Harbor Islands", "Boston Harbor", "National park", 19.95, "10AM-6PM", 300, true));
        attractions.add(new Attraction(17, "MIT Museum", "Cambridge", "Science museum", 18.0, "10AM-5PM", 200, true));
        attractions.add(new Attraction(18, "Arnold Arboretum", "Jamaica Plain", "Botanical garden", 0.0, "Sunrise-Sunset", 500, true));
        attractions.add(new Attraction(19, "Samuel Adams Brewery", "Jamaica Plain", "Brewery tours", 10.0, "11AM-3PM", 100, true));
        attractions.add(new Attraction(20, "Charles River Esplanade", "Back Bay", "Waterfront park", 0.0, "24 Hours", 800, true));

        // Initialize Hotels
        hotels.add(new Hotel(1, "Boston Park Plaza", "Back Bay", 200, 250.0, "WiFi, Pool, Spa", 4, true));
        hotels.add(new Hotel(2, "Harbor View Hotel", "Waterfront", 150, 300.0, "Spa, Restaurant, Ocean View", 5, true));
        hotels.add(new Hotel(3, "Copley Square Hotel", "Back Bay", 180, 280.0, "Restaurant, Business Center", 4, true));
        hotels.add(new Hotel(4, "Lenox Hotel", "Back Bay", 160, 270.0, "Fitness Center, Restaurant", 4, true));
        hotels.add(new Hotel(5, "Revere Hotel", "Theatre District", 190, 240.0, "Rooftop Bar, Pool", 4, true));
        // Add more hotels...

        // Initialize Restaurants
        restaurants.add(new Restaurant(1, "Legal Sea Foods", "Seafood", "Waterfront", 150, "11AM-10PM", 65.0, true));
        restaurants.add(new Restaurant(2, "Union Oyster House", "Seafood", "North End", 100, "11AM-9PM", 55.0, true));
        restaurants.add(new Restaurant(3, "Giacomo's", "Italian", "North End", 80, "4PM-10PM", 45.0, true));
        restaurants.add(new Restaurant(4, "Mike's Pastry", "Dessert", "North End", 60, "8AM-10PM", 15.0, true));
        restaurants.add(new Restaurant(5, "Regina Pizzeria", "Italian", "North End", 90, "11AM-11PM", 35.0, true));
        // Add more restaurants...

        // Initialize Tours
        tours.add(new Tour(1, "Freedom Trail Walking Tour", "John Smith", 20, 25.0));
        tours.add(new Tour(2, "Boston Harbor Cruise", "Maria Garcia", 30, 35.0));
        tours.add(new Tour(3, "Historic Boston Tour", "David Chen", 15, 20.0));
        tours.add(new Tour(4, "North End Food Tour", "Sarah Johnson", 12, 40.0));
        tours.add(new Tour(5, "Boston Ghost Tour", "Michael Brown", 18, 30.0));
        // Add more tours...
    }

    private void initializeGuides() {
        guides.add(new TourGuide(1, "John Smith", "guide1", "guide123",
                "617-555-0101", true));
        guides.add(new TourGuide(2, "Maria Garcia", "guide2", "guide456",
                "617-555-0102", true));
        guides.add(new TourGuide(3, "David Chen", "guide3", "pass789",
                "617-555-0103", true));
        guides.add(new TourGuide(4, "Sarah Johnson", "guide4", "pass321",
                "617-555-0104", true));
    }

    private void initializeEvents() {
        events.add(new Event(1, "Boston Food Festival", "Boston Common", "Annual food festival", 25.0, "2024-12-15", "10AM-8PM", 1000, true, "Food & Culture", "Boston Events Co"));
        events.add(new Event(2, "Harbor Music Festival", "Seaport District", "Live music performances", 35.0, "2024-12-20", "4PM-11PM", 500, true, "Music", "Harbor Entertainment"));
        events.add(new Event(3, "Winter Lights Show", "Public Garden", "Light display and ice sculptures", 15.0, "2024-12-25", "6PM-10PM", 2000, true, "Entertainment", "City Parks Dept"));
        events.add(new Event(4, "New Year Celebration", "Faneuil Hall", "Family-friendly celebration", 50.0, "2024-12-31", "8PM-1AM", 1500, true, "Holiday", "Boston Celebrations"));
        events.add(new Event(5, "Boston Marathon", "Copley Square", "Annual marathon event", 150.0, "2024-04-15", "8AM-5PM", 30000, true, "Sports", "Boston Athletic Association"));
        events.add(new Event(6, "Art in the Park", "Boston Common", "Outdoor art exhibition", 0.0, "2024-06-10", "11AM-6PM", 1000, true, "Arts", "Boston Arts Council"));
        events.add(new Event(7, "Jazz Festival", "Back Bay", "Jazz music festival", 45.0, "2024-07-15", "2PM-10PM", 800, true, "Music", "Boston Jazz Society"));
        events.add(new Event(8, "Science Exhibition", "Museum of Science", "Interactive science expo", 30.0, "2024-08-20", "10AM-6PM", 1200, true, "Education", "Boston Science Foundation"));
        events.add(new Event(9, "Cultural Festival", "Chinatown", "Cultural celebrations", 10.0, "2024-09-05", "11AM-9PM", 2000, true, "Culture", "Boston Cultural Committee"));
        events.add(new Event(10, "Beer Festival", "Seaport District", "Craft beer tasting", 55.0, "2024-10-01", "1PM-9PM", 1500, true, "Food & Drink", "Boston Brewers Association"));
        events.add(new Event(11, "Film Festival", "Theatre District", "Independent films", 40.0, "2024-11-10", "12PM-11PM", 600, true, "Entertainment", "Boston Film Society"));
        events.add(new Event(12, "Tech Conference", "Convention Center", "Technology showcase", 200.0, "2024-03-15", "9AM-6PM", 3000, true, "Technology", "Boston Tech Alliance"));
        events.add(new Event(13, "Book Fair", "Boston Public Library", "Literary event", 5.0, "2024-05-20", "10AM-5PM", 800, true, "Education", "Boston Library Foundation"));
        events.add(new Event(14, "Fashion Show", "Prudential Center", "Fashion showcase", 75.0, "2024-09-15", "7PM-11PM", 500, true, "Fashion", "Boston Fashion Council"));
        events.add(new Event(15, "Sports Expo", "TD Garden", "Sports exhibition", 25.0, "2024-08-01", "10AM-7PM", 2000, true, "Sports", "Boston Sports Commission"));
        events.add(new Event(16, "Wine Tasting", "Back Bay", "Wine festival", 65.0, "2024-07-10", "3PM-9PM", 400, true, "Food & Drink", "Boston Wine Society"));
        events.add(new Event(17, "Comedy Festival", "Wang Theatre", "Stand-up comedy", 45.0, "2024-06-20", "8PM-11PM", 800, true, "Entertainment", "Boston Comedy Club"));
        events.add(new Event(18, "Dance Festival", "Opera House", "Dance performances", 35.0, "2024-04-25", "7PM-10PM", 600, true, "Arts", "Boston Dance Alliance"));
        events.add(new Event(19, "Health & Wellness Expo", "Hynes Convention Center", "Wellness event", 20.0, "2024-03-01", "9AM-5PM", 1500, true, "Health", "Boston Health Coalition"));
        events.add(new Event(20, "Holiday Market", "Faneuil Hall", "Christmas market", 0.0, "2024-12-01", "10AM-8PM", 3000, true, "Holiday", "Boston Markets Association"));
    }

    private void initializeTourists() {
        tourists.add(new Tourist(1, "John Smith", "tourist", "tourist123",
                "anup.bochare.7@gmail.com", "617-555-0101", "USA", "P123456",
                "Cultural Tours", true, 0, 0));

        tourists.add(new Tourist(2, "Maria Garcia", "tourist2", "tourist2123",
                "bochare.a@northeastern.edu", "617-555-0102", "Spain", "P234567",
                "Food Tours", true, 0, 0));

        tourists.add(new Tourist(3, "David Chen", "tourist3", "tourist3123",
                "david@gmail.com", "617-555-0103", "China", "P345678",
                "Historical Sites", true, 0, 0));

        tourists.add(new Tourist(4, "Sarah Johnson", "tourist4", "tourist4123",
                "sarah@northeastern.edu", "617-555-0104", "UK", "P456789",
                "Art Museums", true, 0, 0));

        tourists.add(new Tourist(5, "James Wilson", "tourist5", "tourist5123",
                "james@gmail.com", "617-555-0105", "Canada", "P567890",
                "Nature Tours", true, 0, 0));

        tourists.add(new Tourist(6, "Emma Brown", "tourist6", "tourist6123",
                "emma@northeastern.edu", "617-555-0106", "Australia", "P678901",
                "Adventure Tours", true, 0, 0));

        tourists.add(new Tourist(7, "Luis Rodriguez", "tourist7", "tourist7123",
                "luis@gmail.com", "617-555-0107", "Mexico", "P789012",
                "Food Tours", true, 0, 0));

        tourists.add(new Tourist(8, "Anna Kowalski", "tourist8", "tourist8123",
                "anna@northeastern.edu", "617-555-0108", "Poland", "P890123",
                "Historical Sites", true, 0, 0));

        tourists.add(new Tourist(9, "Mohammed Ahmed", "tourist9", "tourist9123",
                "mohammed@gmail.com", "617-555-0109", "UAE", "P901234",
                "Shopping Tours", true, 0, 0));

        tourists.add(new Tourist(10, "Yuki Tanaka", "tourist10", "tourist10123",
                "yuki@northeastern.edu", "617-555-0110", "Japan", "P012345",
                "Cultural Tours", true, 0, 0));
    }

    private void initializeHospitalityData() {
        // Hotels
        properties.add(new HospitalityProperty(1, "Marriott Downtown", "Hotel", 250.0, 450.0, true, "Hotel Manager - Marriott"));
        properties.add(new HospitalityProperty(2, "Hilton Garden", "Hotel", 180.0, 300.0, true, "Hotel Manager - Hilton"));
        properties.add(new HospitalityProperty(3, "Ritz-Carlton Boston", "Hotel", 400.0, 800.0, true, "Hotel Manager - Ritz"));
        properties.add(new HospitalityProperty(4, "Four Seasons Boston", "Hotel", 450.0, 900.0, true, "Hotel Manager - Four Seasons"));
        properties.add(new HospitalityProperty(5, "Westin Copley Place", "Hotel", 300.0, 600.0, true, "Hotel Manager - Westin"));
        properties.add(new HospitalityProperty(6, "Sheraton Boston", "Hotel", 250.0, 500.0, true, "Hotel Manager - Sheraton"));
        properties.add(new HospitalityProperty(7, "Boston Harbor Hotel", "Hotel", 350.0, 700.0, true, "Hotel Manager - Harbor"));
        properties.add(new HospitalityProperty(8, "Lenox Hotel", "Hotel", 280.0, 550.0, true, "Hotel Manager - Lenox"));
        properties.add(new HospitalityProperty(9, "Copley Square Hotel", "Hotel", 220.0, 440.0, true, "Hotel Manager - Copley"));
        properties.add(new HospitalityProperty(10, "W Boston", "Hotel", 320.0, 640.0, true, "Hotel Manager - W Hotels"));

        // Restaurants
        properties.add(new HospitalityProperty(11, "Legal Sea Foods", "Fine Dining", 60.0, 120.0, true, "Restaurant Manager - Legal"));
        properties.add(new HospitalityProperty(12, "Abe & Louie's", "Fine Dining", 80.0, 160.0, true, "Restaurant Manager - Abe & Louie's"));
        properties.add(new HospitalityProperty(13, "Atlantic Fish Co", "Fine Dining", 70.0, 140.0, true, "Restaurant Manager - Atlantic"));
        properties.add(new HospitalityProperty(14, "Giacomo's", "Italian Dining", 40.0, 80.0, true, "Restaurant Manager - Giacomo's"));
        properties.add(new HospitalityProperty(15, "Union Oyster House", "Seafood", 55.0, 110.0, true, "Restaurant Manager - Union"));
        properties.add(new HospitalityProperty(16, "Mooo Restaurant", "Steakhouse", 75.0, 150.0, true, "Restaurant Manager - Mooo"));
        properties.add(new HospitalityProperty(17, "O Ya", "Japanese", 150.0, 300.0, true, "Restaurant Manager - O Ya"));
        properties.add(new HospitalityProperty(18, "Mistral", "French", 90.0, 180.0, true, "Restaurant Manager - Mistral"));
        properties.add(new HospitalityProperty(19, "Grill 23", "Steakhouse", 85.0, 170.0, true, "Restaurant Manager - Grill 23"));
        properties.add(new HospitalityProperty(20, "Menton", "Fine Dining", 95.0, 190.0, true, "Restaurant Manager - Menton"));
    }

    private void initializeTransportManagerData() {
        transportManagers = new ArrayList<>();
        transportManagers.add(new TransportManager(1, "John Smith", "transport1",
                "trans123", true));
        transportManagers.add(new TransportManager(2, "Jane Doe", "transport2",
                "trans456", true));
    }

    public List<HospitalityProperty> getPropertiesByManager(String managerType) {
        if (managerType.equals("All")) {
            return new ArrayList<>(properties);
        }
        return properties.stream()
                .filter(p -> p.getManagerType().equals(managerType))
                .collect(Collectors.toList());
    }

    public void addProperty(HospitalityProperty property) {
        properties.add(property);
    }

    public void updateProperty(HospitalityProperty property) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropertyId() == property.getPropertyId()) {
                properties.set(i, property);
                break;
            }
        }
    }

    public void deleteProperty(int propertyId) {
        properties.removeIf(p -> p.getPropertyId() == propertyId);
    }

    public void registerUserManagementPanel(UserManagementJPanel panel) {
        if (!userManagementPanels.contains(panel)) {
            userManagementPanels.add(panel);
        }
    }

    public void notifyUserManagementPanel() {
        for (UserManagementJPanel panel : userManagementPanels) {
            panel.refreshData();
        }
    }

    public void bookAttraction(Attraction attraction, String username) {
        if (attraction.getCapacity() > 0) {
            attraction.setCapacity(attraction.getCapacity() - 1);
            for (Tourist tourist : tourists) {
                if (tourist.getUsername().equals(username)) {
                    tourist.incrementAttractionsBooked();
                    notifyUserManagementPanel();
                    break;
                }
            }
        }
    }

    public void bookEvent(Event event, String username) {
        if (event.getCapacity() > 0) {
            event.setCapacity(event.getCapacity() - 1);
            for (Tourist tourist : tourists) {
                if (tourist.getUsername().equals(username)) {
                    tourist.incrementEventsBooked();
                    notifyUserManagementPanel();
                    break;
                }
            }
        }
    }

    public List<Tourist> getAvailableTourists() {
        return new ArrayList<>(tourists);
    }

    public void addTourist(Tourist tourist) {
        tourists.add(tourist);
    }

    public void updateTourist(Tourist tourist) {
        for (int i = 0; i < tourists.size(); i++) {
            if (tourists.get(i).getTouristId() == tourist.getTouristId()) {
                tourists.set(i, tourist);
                break;
            }
        }
    }

    public void deleteTourist(int touristId) {
        tourists.removeIf(t -> t.getTouristId() == touristId);
    }

    public List<Attraction> getAvailableAttractions() {
        return new ArrayList<>(attractions);
    }

    public List<Tour> getAvailableTours() {
        return new ArrayList<>(tours);
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
        notifyTouristPanels();
    }

    public void updateAttraction(Attraction attraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equals(attraction.getName())) {
                attractions.set(i, attraction);
                break;
            }
        }
        notifyTouristPanels();
    }

    public void deleteAttraction(String name) {
        attractions.removeIf(a -> a.getName().equals(name));
        notifyTouristPanels();
    }

    public void registerTouristPanel(ViewAttractionAndEventPanel panel) {
        if (!touristPanels.contains(panel)) {
            touristPanels.add(panel);
        }
    }

    private int generateBookingId() {
        return bookings.size() + 1;
    }

    public List<TourGuide> getAvailableGuides() {
        return new ArrayList<>(guides);
    }

    public void addGuide(TourGuide guide) {
        guides.add(guide);
    }

    public void updateGuide(TourGuide guide) {
        for (int i = 0; i < guides.size(); i++) {
            if (guides.get(i).getGuideId() == guide.getGuideId()) {
                guides.set(i, guide);
                break;
            }
        }
    }

    public void deleteGuide(int guideId) {
        guides.removeIf(g -> g.getGuideId() == guideId);
    }

    public List<Event> getAvailableEvents() {
        return new ArrayList<>(events);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void updateEvent(Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId() == event.getEventId()) {
                events.set(i, event);
                break;
            }
        }
    }

    public void deleteEvent(int eventId) {
        events.removeIf(e -> e.getEventId() == eventId);
    }

    public void updateAttractionCapacity(String attractionName, int newCapacity) {
        for (Attraction attraction : attractions) {
            if (attraction.getName().equals(attractionName)) {
                attraction.setCapacity(newCapacity);
                // No need for fireTableDataChanged() here
                break;
            }
        }
    }

    public void updateEventCapacity(String eventName, int newCapacity) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                event.setCapacity(newCapacity);
                break;
            }
        }
    }

    public void notifyTouristPanels() {
        for (ViewAttractionAndEventPanel panel : touristPanels) {
            panel.refreshData();
        }
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getTouristBookings(String touristId) {
        return bookings.stream()
                .filter(b -> b.getTouristId().equals(touristId))
                .collect(Collectors.toList());
    }

    public void addTour(Tour tour) {
        if (tour != null) {
            tours.add(tour);
        }
    }

    public void updateTour(Tour tour) {
        for (int i = 0; i < tours.size(); i++) {
            if (tours.get(i).getTourId() == tour.getTourId()) {
                tours.set(i, tour);
                break;
            }
        }
    }

    public void deleteTour(int tourId) {
        tours.removeIf(t -> t.getTourId() == tourId);
    }

    public List<String> getGuideNames() {
        return guides.stream()
                .filter(TourGuide::isActive)
                .map(TourGuide::getName)
                .collect(Collectors.toList());
    }

    public List<TransportationRoute> getRoutes() {
        return routes;
    }

    public void addRoute(TransportationRoute route) {
        if (route != null) {
            routes.add(route);
        }
    }

    public void addEventManager(EventManager manager) {
        eventManagers.add(manager);
    }

    public void updateEventManager(EventManager manager) {
        for (int i = 0; i < eventManagers.size(); i++) {
            if (eventManagers.get(i).getManagerId() == manager.getManagerId()) {
                eventManagers.set(i, manager);
                break;
            }
        }
    }

    public void addTransportManager(TransportManager manager) {
        transportManagers.add(manager);
    }

    public List<TransportManager> getTransportManagers() {
        return new ArrayList<>(transportManagers);
    }

    public void updateTransportManager(TransportManager manager) {
        for (int i = 0; i < transportManagers.size(); i++) {
            if (transportManagers.get(i).getManagerId() == manager.getManagerId()) {
                transportManagers.set(i, manager);
                break;
            }
        }
    }

    public void deleteRoute(String routeName) {
        routes.removeIf(route
                -> route.getRouteName().equals(routeName)
        );
    }

    public void updateRoute(TransportationRoute updatedRoute) {
        for (int i = 0; i < routes.size(); i++) {
            TransportationRoute route = routes.get(i);
            if (route.getRouteName().equals(updatedRoute.getRouteName())) {
                routes.set(i, updatedRoute);
                break;
            }
        }
    }

    public List<ServiceFeedback> getServiceFeedbacks() {
        return new ArrayList<>(serviceFeedbacks);
    }

    public void addServiceFeedback(ServiceFeedback feedback) {
        serviceFeedbacks.add(feedback);
    }

    private void initializeServiceFeebackData() {
        // Add demo feedback data
        serviceFeedbacks.add(new ServiceFeedback(
                "Customer Support",
                "Long Wait Times",
                "Poor",
                "Increase Staff",
                "Waited 45 minutes for support",
                true));

        serviceFeedbacks.add(new ServiceFeedback(
                "Technical Support",
                "Software Bugs",
                "Good",
                "Better Testing",
                "Issue resolved but took time",
                true));

        serviceFeedbacks.add(new ServiceFeedback(
                "Billing Support",
                "Invoice Error",
                "Excellent",
                "Automated Checks",
                "Quick resolution of billing issue",
                true));

        serviceFeedbacks.add(new ServiceFeedback(
                "General Inquiry",
                "Product Information",
                "Average",
                "Updated Documentation",
                "Information was outdated",
                true));

    }

    public void deleteServiceFeedback(ServiceFeedback feedback) {
        serviceFeedbacks.remove(feedback);
    }

    public void deleteServiceFeedbackAtIndex(int index) {
        if (index >= 0 && index < serviceFeedbacks.size()) {
            serviceFeedbacks.remove(index);
        }
    }

    public void removeServiceFeedback(ServiceFeedback feedback) {
        serviceFeedbacks.remove(feedback);
    }
//public void bookAttraction(Attraction attraction, String username) {
//    if (attraction.getCapacity() > 0) {
//        // Update attraction capacity
//        attraction.setCapacity(attraction.getCapacity() - 1);
//        
//        // Update tourist booking count
//        for (Tourist tourist : tourists) {
//            if (tourist.getUsername().equals(username)) {
//                tourist.incrementAttractionsBooked();
//                break;
//            }
//        }
//    }
//}

//public void bookEvent(Event event, String username) {
//    if (event.getCapacity() > 0) {
//        // Update event capacity
//        event.setCapacity(event.getCapacity() - 1);
//        
//        // Update tourist booking count
//        for (Tourist tourist : tourists) {
//            if (tourist.getUsername().equals(username)) {
//                tourist.incrementEventsBooked();
//                break;
//            }
//        }
//    }
//}
    public Tourist getTouristByUsername(String username) {
        for (Tourist tourist : tourists) {
            if (tourist.getUsername().equals(username)) {
                return tourist;
            }
        }
        return null;
    }

}
