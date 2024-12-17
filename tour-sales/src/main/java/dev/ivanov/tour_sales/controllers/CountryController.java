package dev.ivanov.tour_sales.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @GetMapping("/all")
    public ResponseEntity<?> getAllCountries() {

        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCountry() {

        return null;
    }

    @DeleteMapping("/delete/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable String countryId) {
        return null;
    }
}
