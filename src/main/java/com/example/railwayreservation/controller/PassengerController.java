package com.example.railwayreservation.controller;

import com.example.railwayreservation.model.Passenger;
import com.example.railwayreservation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/register")
    public ResponseEntity<Passenger> register(@RequestBody Passenger passenger) {
        return ResponseEntity.ok(passengerService.register(passenger));
    }

    @PostMapping("/login")
    public ResponseEntity<Passenger> login(@RequestParam String username, @RequestParam String password) {
        return passengerService.login(username, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
