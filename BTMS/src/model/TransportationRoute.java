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
public class TransportationRoute {

    private int routeId;
    private String routeName;
    private String startPoint;
    private String endPoint;
    private double fare;
    private String schedule;
    private int vehicleCapacity;
    private boolean isOperating;
    private int availableSeats;
    private String vehicleType;
    private String driverInfo;
    private String routeDescription;
    private int estimatedDuration;
    private List<String> stops;

    // Constructor
    public TransportationRoute(int routeId, String routeName, String startPoint,
            String endPoint, double fare, String schedule, int vehicleCapacity,
            boolean isOperating) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.fare = fare;
        this.schedule = schedule;
        this.vehicleCapacity = vehicleCapacity;
        this.isOperating = isOperating;
        this.availableSeats = vehicleCapacity;
        this.stops = new ArrayList<>();
    }

    // Getters
    public int getRouteId() {
        return routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public double getFare() {
        return fare;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getVehicleCapacity() {
        return vehicleCapacity;
    }

    public boolean isOperating() {
        return isOperating;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getDriverInfo() {
        return driverInfo;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public List<String> getStops() {
        return stops;
    }

    // Setters
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setVehicleCapacity(int vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public void setOperating(boolean operating) {
        isOperating = operating;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    // Business Methods
    public boolean bookSeats(int numberOfSeats) {
        if (availableSeats >= numberOfSeats && isOperating) {
            availableSeats -= numberOfSeats;
            return true;
        }
        return false;
    }

    public boolean cancelBooking(int numberOfSeats) {
        if (availableSeats + numberOfSeats <= vehicleCapacity) {
            availableSeats += numberOfSeats;
            return true;
        }
        return false;
    }

    public void addStop(String stop) {
        stops.add(stop);
    }

    public void removeStop(String stop) {
        stops.remove(stop);
    }

    public boolean hasAvailableSeats(int requiredSeats) {
        return availableSeats >= requiredSeats;
    }

    @Override
    public String toString() {
        return "TransportationRoute{"
                + "routeName='" + routeName + '\''
                + ", startPoint='" + startPoint + '\''
                + ", endPoint='" + endPoint + '\''
                + ", fare=" + fare
                + ", availableSeats=" + availableSeats
                + '}';
    }
}
