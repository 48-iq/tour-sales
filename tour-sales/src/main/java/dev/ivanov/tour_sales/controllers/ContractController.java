package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.CreateContractDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @PostMapping("/create")
    public ResponseEntity<?> createContract(@RequestBody CreateContractDto createContractDto) {
        return null;
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getContractsByUser(@PathVariable String userId) {
        return null;
    }

    @GetMapping("/by-tour/{tourId}")
    public ResponseEntity<?> getContractsByTour(@PathVariable String tourId) {
        return null;
    }

    @PutMapping("/pay-for/{contractId}")
    public ResponseEntity<?> payForContract(@PathVariable String contractId) {
        return null;
    }

    @DeleteMapping("/delete/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable String contractId) {
        return null;
    }
}
