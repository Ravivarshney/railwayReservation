package com.example.railwayreservation.dao;

import com.example.railwayreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByPnrNumber(String pnrNumber);
}