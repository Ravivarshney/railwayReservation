package com.example.railwayreservation.controller;

import com.example.railwayreservation.dao.PassengerRepository;
import com.example.railwayreservation.dao.TrainRepository;
import com.example.railwayreservation.model.Passenger;
import com.example.railwayreservation.model.Reservation;
import com.example.railwayreservation.model.Train;
import com.example.railwayreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private PassengerRepository passengerRepository; // To fetch Passenger by ID

    @Autowired
    private TrainRepository trainRepository;

    @PostMapping
    public ResponseEntity<Reservation> reserveTicket(@RequestParam Long passengerId, @RequestParam Long trainId) {
        // Fetch the Passenger and Train objects from the repositories
        Optional<Passenger> passengerOpt = passengerRepository.findById(passengerId);
        Optional<Train> trainOpt = trainRepository.findById(trainId);

        if (passengerOpt.isPresent() && trainOpt.isPresent()) {
            Passenger passenger = passengerOpt.get();
            Train train = trainOpt.get();
            Reservation reservation = reservationService.reserveTicket(passenger, train);
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{pnrNumber}")
    public ResponseEntity<Void> cancelTicket(@PathVariable String pnrNumber) {
        reservationService.cancelTicket(pnrNumber);
        return ResponseEntity.ok().build();
    }
}