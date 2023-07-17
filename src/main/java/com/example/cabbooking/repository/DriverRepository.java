package com.example.cabbooking.repository;

import com.example.cabbooking.models.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DriverRepository {

    private final List<Driver> drivers;

    public DriverRepository() {
        this.drivers = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.stream()
                .filter(driver -> driver.isAvailable())
                .collect(Collectors.toList());
    }

    public Driver getDriverByName(String driverName) {
        return drivers.stream()
                .filter(driver -> driver.getName().equals(driverName))
                .findFirst()
                .orElse(null);
    }

    public void updateDriver(Driver driver) {
        if (driver != null) {
            int index = drivers.indexOf(driver);
            if (index != -1) {
                drivers.set(index, driver);
            }
        }
    }

}
