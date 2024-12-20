package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.contract.ContractCreateDto;
import dev.ivanov.tour_sales.dto.contract.ContractDto;
import dev.ivanov.tour_sales.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/create")
    public ResponseEntity<?> createContract(@RequestBody ContractCreateDto contractCreateDto) {
        contractService.createContract(contractCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getContractsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(contractService.getContractsByUser(userId));
    }

    @GetMapping("/by-tour/{tourId}")
    public ResponseEntity<?> getContractsByTour(@PathVariable String tourId) {
        return ResponseEntity.ok(contractService.getContractsByTour(tourId));
    }

    @PutMapping("/pay-for/{contractId}")
    public ResponseEntity<?> payForContract(@PathVariable String contractId) {
        contractService.updateContract(contractId, true);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable String contractId) {
        contractService.deleteContract(contractId);
        return ResponseEntity.ok().build();
    }
}
