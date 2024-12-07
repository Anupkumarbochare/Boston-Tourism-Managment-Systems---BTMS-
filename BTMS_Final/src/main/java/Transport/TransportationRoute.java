/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anupbochare
 */
public class TransportationRoute {
private int routeId;
    private String routeName;
    private String startLocation;
    private String endLocation;
    private double price;
    private int frequency;
    private int capacity;
    private boolean routeActiveStatus;
    
    public TransportationRoute(int capacity, String routeName, String startLocation, String endLocation, double price, int frequency, int capacity1, boolean routeActiveStatus) {
 
        this.routeName = routeName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.price = price;
        this.frequency = frequency;
        this.capacity = capacity;
        this.routeActiveStatus = routeActiveStatus;
    }
    

    
    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    public String getStartLocation() { return startLocation; }
    public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

    public String getEndLocation() { return endLocation; }
    public void setEndLocation(String endLocation) { this.endLocation = endLocation; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public boolean isRouteActiveStatus() { return routeActiveStatus; }
    public void setRouteActiveStatus(boolean routeActiveStatus) { 
        this.routeActiveStatus = routeActiveStatus; 
    }

    public void add(TransportationRoute transportationroute) {
        
}
    
}
