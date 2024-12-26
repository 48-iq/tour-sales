package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.contract.ContractCreateDto;
import dev.ivanov.tour_sales.dto.contract.ContractDto;
import dev.ivanov.tour_sales.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/create")
    public ResponseEntity<?> createContract(@RequestBody ContractCreateDto contractCreateDto) {
        contractService.createContract(contractCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @DeleteMapping("/delete/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable String contractId) {
        contractService.deleteContract(contractId);
        return ResponseEntity.ok().build();
    }
}
