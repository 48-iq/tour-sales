package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.city.CityCreateDto;
import dev.ivanov.tour_sales.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PostMapping("/create")
    public ResponseEntity<?> add(@RequestBody CityCreateDto cityCreateDto) {
        cityService.createCity(cityCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{cityName}")
    public ResponseEntity<?> deleteCity(@PathVariable String cityName) {
        cityService.deleteCity(cityName);
        return ResponseEntity.ok().build();
    }
}
