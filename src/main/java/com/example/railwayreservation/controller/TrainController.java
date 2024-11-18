package com.example.railwayreservation.controller;

import com.example.railwayreservation.model.Train;
import com.example.railwayreservation.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping
    public ResponseEntity<List<Train>> getTrains(@RequestParam String source, @RequestParam String destination) {
        return ResponseEntity.ok(trainService.getTrains(source, destination));
    }

    @PutMapping
    public ResponseEntity<Void> updateTrain(@RequestBody Train train) {
        trainService.updateTrainDetails(train);
        return ResponseEntity.ok().build();
    }
}