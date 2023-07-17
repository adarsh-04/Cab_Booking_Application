package com.example.cabbooking.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Driver {
    
    @NotEmpty(message = "Name is required")
    private String name;
    
    @NotEmpty(message = "Gender is required")
    private String gender;
    
    @NotEmpty(message = "Vehicle is required")
    private String vehicle;
    
    @NotEmpty(message = "Vehicle number is required")
    private String vehicleNumber;
    
    @NotNull(message = "Current location is required")
    private Location currentLocation;
    
    private boolean available;
    
    public Driver() {
    }

    public Driver(String name, String gender, String vehicle, String vehicleNumber, Location currentLocation) {
        this.name = name;
        this.gender = gender;
        this.vehicle = vehicle;
        this.vehicleNumber = vehicleNumber;
        this.currentLocation = currentLocation;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
