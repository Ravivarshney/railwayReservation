package com.example.railwayreservation.service;

import com.example.railwayreservation.dao.ReservationRepository;
import com.example.railwayreservation.dao.TrainRepository;
import com.example.railwayreservation.model.Passenger;
import com.example.railwayreservation.model.Reservation;
import com.example.railwayreservation.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TrainRepository trainRepository;

    public Reservation reserveTicket(Passenger passenger, Train train) {
        // Check availability and create reservation
        if (train.getAvailableSeats() > 0) {
            Reservation reservation = new Reservation();
            reservation.setPassenger(passenger);
            reservation.setTrain(train);
            reservation.setPnrNumber(generatePnrNumber());
            train.setAvailableSeats(train.getAvailableSeats() - 1);
            trainRepository.save(train);
            return reservationRepository.save(reservation);
        }
        throw new RuntimeException("No available seats");
    }

    public void cancelTicket(String pnrNumber) {
        reservationRepository.findByPnrNumber(pnrNumber).ifPresent(reservation -> {
            Train train = reservation.getTrain();
            train.setAvailableSeats(train.getAvailableSeats() + 1);
            trainRepository.save(train);
            reservationRepository.delete(reservation);
        });
    }

    private String generatePnrNumber() {
        // Implement PNR number generation logic
        return UUID.randomUUID().toString();
    }
}