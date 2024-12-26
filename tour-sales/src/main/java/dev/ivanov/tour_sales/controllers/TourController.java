package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.tour.TourCreateDto;
import dev.ivanov.tour_sales.dto.tour.TourUpdateDto;
import dev.ivanov.tour_sales.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/by-id/{tourId}")
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

    @GetMapping("/all")
    public ResponseEntity<?> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    @GetMapping("/find")
    public ResponseEntity<?> findTours(@RequestParam String title,
                                       @RequestParam String companyId) {
        if (title.isEmpty() && companyId.isEmpty()) {
            return ResponseEntity.ok(tourService.getAllTours());
        } else if (!title.isEmpty() && !companyId.isEmpty()) {
            return ResponseEntity.ok(tourService.getToursByTitleAndCompanyId(title, companyId));
        } else if (!title.isEmpty()) {
            return ResponseEntity.ok(tourService.getToursByTitle(title));
        } else {
            return ResponseEntity.ok(tourService.getToursByCompanyId(companyId));
        }
    }

    @GetMapping("/by-company/{companyId}")
    public ResponseEntity<?> getToursByCompany(@PathVariable String companyId) {
        return ResponseEntity.ok(tourService.getToursByCompanyId(companyId));
    }

    @PostMapping("/add-city/{tourId}/{cityId}")
    public ResponseEntity<?> addCityToTour(@PathVariable String tourId, @PathVariable String cityId) {
        tourService.addCityToTour(cityId, tourId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove-city/{tourId}/{cityId}")
    public ResponseEntity<?> removeCityFromTour(@PathVariable String tourId, @PathVariable String cityId) {
        tourService.removeCityFromTour(cityId, tourId);
        return ResponseEntity.ok().build();
    }
}
