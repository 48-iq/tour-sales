package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.city.AddCityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(AddCityDto addCityDto) {
        return null;
    }

    @DeleteMapping("/delete/{cityName}")
    public ResponseEntity<?> deleteCity(@PathVariable String cityName) {
        return null;
    }
}
