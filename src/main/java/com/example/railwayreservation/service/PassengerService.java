package com.example.railwayreservation.service;

import com.example.railwayreservation.dao.PassengerRepository;
import com.example.railwayreservation.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger register(Passenger passenger) {
        // Implement registration logic
        return passengerRepository.save(passenger);
    }

    public Optional<Passenger> login(String username, String password) {
        // Implement login logic
        return passengerRepository.findByUsername(username)
                .filter(passenger -> passenger.getPassword().equals(password));
    }
}
