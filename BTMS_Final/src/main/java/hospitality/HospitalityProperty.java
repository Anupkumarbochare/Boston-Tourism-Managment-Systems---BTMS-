/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitality;

/**
 *
 * @author anupbochare
 */
public class HospitalityProperty {

    private int propertyId;
    private String name;
    private String type;
    private double floorPrice;
    private double ceilingPrice;
    private boolean isActive;
    private String managerType;

    public HospitalityProperty(int propertyId, String name, String type,
            double floorPrice, double ceilingPrice, boolean isActive, String managerType) {
        this.propertyId = propertyId;
        this.name = name;
        this.type = type;
        this.floorPrice = floorPrice;
        this.ceilingPrice = ceilingPrice;
        this.isActive = isActive;
        this.managerType = managerType;
    }

    // Standard getters
    public int getPropertyId() {
        return propertyId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getFloorPrice() {
        return floorPrice;
    }

    public double getCeilingPrice() {
        return ceilingPrice;
    }

    public boolean isActive() {
        return isActive;
    } // Changed from isIsActive

    public String getManagerType() {
        return managerType;
    }

    // Standard setters
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFloorPrice(double floorPrice) {
        this.floorPrice = floorPrice;
    }

    public void setCeilingPrice(double ceilingPrice) {
        this.ceilingPrice = ceilingPrice;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }
}
