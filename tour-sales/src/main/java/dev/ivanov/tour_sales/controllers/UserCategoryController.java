package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.category.UserCategoryCreateDto;
import dev.ivanov.tour_sales.services.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-category")
public class UserCategoryController {
    @Autowired
    private UserCategoryService userCategoryService;

    @PostMapping("/create")
    public ResponseEntity<?> addCategory(@RequestBody UserCategoryCreateDto userCategoryCreateDto) {
        userCategoryService.createUserCategory(userCategoryCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(userCategoryService.getAllUserCategories());
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
        userCategoryService.deleteUserCategory(categoryId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-user/{categoryId}/{userId}")
    public ResponseEntity<?> addUserToCategory(@PathVariable String categoryId,
                                               @PathVariable String userId) {
        userCategoryService.addUserToCategory(categoryId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove-user/{categoryId}/{userId}")
    public ResponseEntity<?> removeUserFromCategory(@PathVariable String categoryId,
                                                    @PathVariable String userId) {
        userCategoryService.removeUserFromCategory(categoryId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getCategoriesByUser(@PathVariable String userId) {
        return ResponseEntity.ok(userCategoryService.getCategoriesByUserId(userId));
    }


}
