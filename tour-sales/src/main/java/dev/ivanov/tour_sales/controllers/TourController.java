package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.CreateTourDto;
import dev.ivanov.tour_sales.dto.UpdateTourDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tour")
public class TourController {

    @GetMapping("/all")
    public ResponseEntity<?> getAllTours() {
        return null;
    }

    @GetMapping("/{tourId}")
    public ResponseEntity<?> getTour(@PathVariable String tourId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTour(@RequestBody CreateTourDto createTourDto) {
        return null;
    }

    @PutMapping("/update/{tourId}")
    public ResponseEntity<?> updateTour(@PathVariable String tourId,
                                        @RequestBody UpdateTourDto updateTourDto) {
        return null;
    }

    @DeleteMapping("/delete/{tourId}")
    public ResponseEntity<?> deleteTour(@PathVariable String tourId) {

        return null;
    }
}
