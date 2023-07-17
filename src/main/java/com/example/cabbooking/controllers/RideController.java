package com.example.cabbooking.controllers;

import com.example.cabbooking.models.Driver;
import com.example.cabbooking.models.User;
import com.example.cabbooking.services.RideService;
import com.example.cabbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;
    private final UserService userService;

    @Autowired
    public RideController(RideService rideService, UserService userService) {
        this.rideService = rideService;
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user: " + e.getMessage());
        }
    }

    @PostMapping("/drivers")
    public ResponseEntity<String> addDriver(@Valid @RequestBody Driver driver) {
        try {
            rideService.addDriver(driver);
            return ResponseEntity.ok("Driver added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add driver: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Driver>> findRide(@RequestParam String username, @RequestParam("source") String source) {
        try {
            List<Driver> availableDrivers = rideService.findRide(username, source);
            return ResponseEntity.ok(availableDrivers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PostMapping("/rides/{username}/choose")
    public ResponseEntity<String> chooseRide(
            @PathVariable String username,
            @RequestParam String driverName
    ) {
        try {
            rideService.chooseRide(username, driverName);
            return ResponseEntity.ok("Ride chosen successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to choose ride: " + e.getMessage());
        }
    }
}
