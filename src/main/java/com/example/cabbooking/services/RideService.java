package com.example.cabbooking.services;

import com.example.cabbooking.models.Driver;
import com.example.cabbooking.models.User;
import com.example.cabbooking.repository.DriverRepository;
import com.example.cabbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public RideService(UserRepository userRepository, DriverRepository driverRepository) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    public void addDriver(Driver driver) {
        if (driver == null || driver.getName() == null || driver.getName().isEmpty()) {
            throw new IllegalArgumentException("Invalid driver details");
        }
        driverRepository.addDriver(driver);
    }

    public List<Driver> findRide(String username, String source) {
        
        double sourceLatitude;
        double sourceLongitude;
        try {
            String[] coordinates = source.split(",");
            sourceLatitude = Double.parseDouble(coordinates[0]);
            sourceLongitude = Double.parseDouble(coordinates[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid source coordinates");
        }
    
        // Retrieve the user by username
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
    
        List<Driver> availableDrivers = driverRepository.getAvailableDrivers();
    
        List<Driver> matchingDrivers = new ArrayList<>();
        for (Driver driver : availableDrivers) {
            double distance = calculateDistance(sourceLatitude, sourceLongitude, driver.getCurrentLocation().getLatitude(), driver.getCurrentLocation().getLongitude());
            if (distance <= 5) {
                matchingDrivers.add(driver);
            }
        }
    
        return matchingDrivers;
    }
    

    public void chooseRide(String username, String driverName) {
        if (username == null || username.isEmpty() || driverName == null || driverName.isEmpty()) {
            throw new IllegalArgumentException("Invalid input for choosing a ride");
        }

        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Driver driver = driverRepository.getDriverByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException("Driver not found");
        }

        if (driver.isAvailable()) {
            driver.setAvailable(false);
            driverRepository.updateDriver(driver);
        } else {
            throw new IllegalArgumentException("Driver is not available");
        }
    }

    public double calculateDistance(double sourceLatitude, double sourceLongitude, double destinationLatitude, double destinationLongitude) {
        double latDistance = Math.abs(sourceLatitude - destinationLatitude);
        double lonDistance = Math.abs(sourceLongitude - destinationLongitude);
        
        double distance = Math.sqrt(Math.pow(latDistance, 2) + Math.pow(lonDistance, 2));
        
        return distance;
    }
    
}
