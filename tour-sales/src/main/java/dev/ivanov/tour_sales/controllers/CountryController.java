package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCountries() {
        return  ResponseEntity.ok(countryService.getAllCountries());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCountry(@RequestParam String name) {
        countryService.createCountry(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable String countryId) {
        countryService.deleteCountry(countryId);
        return ResponseEntity.ok().build();
    }
}
