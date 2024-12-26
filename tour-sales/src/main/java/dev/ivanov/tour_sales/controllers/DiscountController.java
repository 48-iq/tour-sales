package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.discount.DiscountCreateDto;
import dev.ivanov.tour_sales.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @PostMapping("/create")
    public ResponseEntity<?> createDiscount(DiscountCreateDto discountCreateDto) {
        discountService.createDiscount(discountCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{categoryName}/{tourId}")
    public ResponseEntity<?> deleteDiscount(@PathVariable String categoryName,
                                            @PathVariable String tourId) {
        discountService.deleteDiscount(categoryName, tourId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-tour/{tourId}")
    public ResponseEntity<?> getDiscountsByTour(@PathVariable String tourId) {
        return ResponseEntity.ok(discountService.getDiscountsByTour(tourId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

}
