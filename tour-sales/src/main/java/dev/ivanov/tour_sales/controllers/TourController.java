package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.tour.TourCreateDto;
import dev.ivanov.tour_sales.dto.tour.TourUpdateDto;
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
    public ResponseEntity<?> createTour(@RequestBody TourCreateDto tourCreateDto) {
        return null;
    }

    @PutMapping("/update/{tourId}")
    public ResponseEntity<?> updateTour(@PathVariable String tourId,
                                        @RequestBody TourUpdateDto tourUpdateDto) {
        return null;
    }

    @DeleteMapping("/delete/{tourId}")
    public ResponseEntity<?> deleteTour(@PathVariable String tourId) {

        return null;
    }
}
