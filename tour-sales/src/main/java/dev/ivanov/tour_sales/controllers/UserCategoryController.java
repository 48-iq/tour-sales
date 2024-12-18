package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.category.UserCategoryCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-category")
public class UserCategoryController {

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody UserCategoryCreateDto userCategoryCreateDto) {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        return null;
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
        return null;
    }
}
