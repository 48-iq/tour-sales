package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.tour.TourCreateDto;
import dev.ivanov.tour_sales.dto.tour.TourUpdateDto;
import dev.ivanov.tour_sales.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/{tourId}")
    public ResponseEntity<?> getTour(@PathVariable String tourId) {
        return ResponseEntity.ok(tourService.getTourById(tourId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTour(@RequestBody TourCreateDto tourCreateDto) {
        tourService.createTour(tourCreateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{tourId}")
    public ResponseEntity<?> updateTour(@PathVariable String tourId,
                                        @RequestBody TourUpdateDto tourUpdateDto) {
        tourService.updateTourById(tourId, tourUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{tourId}")
    public ResponseEntity<?> deleteTour(@PathVariable String tourId) {
        tourService.deleteTourById(tourId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<?> findToursByNameOrDescription(@RequestParam String title,
                                                          @RequestParam String city) {
        return ResponseEntity.ok(tourService.getToursByTitleAndCity(title, city));
    }

    @GetMapping("/by-company/{companyId}")
    public ResponseEntity<?> getToursByCompany(@PathVariable String companyId) {
        return ResponseEntity.ok(tourService.getToursByCompanyId(companyId));
    }
}
