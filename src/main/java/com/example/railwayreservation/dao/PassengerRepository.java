package com.example.railwayreservation.dao;

import com.example.railwayreservation.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByUsername(String username);
}
