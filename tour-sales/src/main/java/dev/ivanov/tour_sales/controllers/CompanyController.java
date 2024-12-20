package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.company.CompanyCreateDto;
import dev.ivanov.tour_sales.dto.company.CompanyUpdateDto;
import dev.ivanov.tour_sales.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<?> createCompany(@RequestBody CompanyCreateDto companyCreateDto) {
        companyService.createCompany(companyCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompany(@PathVariable String companyId) {
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<?> updateCompany(@PathVariable String companyId,
                                           @RequestBody CompanyUpdateDto companyUpdateDto) {
        companyService.updateCompanyById(companyId, companyUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable String companyId) {
        companyService.deleteCompanyById(companyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<?> findCompaniesByNameOrEmail(@RequestParam String query) {
        return ResponseEntity.ok(companyService.findCompaniesByNameOrEmail(query));
    }
}
