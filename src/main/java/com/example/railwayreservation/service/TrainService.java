package com.example.railwayreservation.service;

import com.example.railwayreservation.dao.TrainRepository;
import com.example.railwayreservation.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;

    public List<Train> getTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }

    public void updateTrainDetails(Train train) {
        trainRepository.save(train);
    }
}