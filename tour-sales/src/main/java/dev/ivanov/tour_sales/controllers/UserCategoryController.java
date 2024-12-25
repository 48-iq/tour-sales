package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.category.UserCategoryCreateDto;
import dev.ivanov.tour_sales.services.UserCategoryService;
import dev.ivanov.tour_sales.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-categories")
public class UserCategoryController {
    @Autowired
    private UserCategoryService userCategoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> addCategory(@RequestBody UserCategoryCreateDto userCategoryCreateDto) {
        userCategoryService.createUserCategory(userCategoryCreateDto);
        return ResponseEntity.ok(userCategoryService.getAllUserCategories());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(userCategoryService.getAllUserCategories());
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
        userCategoryService.deleteUserCategory(categoryId);
        return ResponseEntity.ok(userCategoryService.getAllUserCategories());
    }

    @PostMapping("/add-user/{categoryId}/{userId}")
    public ResponseEntity<?> addUserToCategory(@PathVariable String categoryId,
                                               @PathVariable String userId) {
        userCategoryService.addUserToCategory(userId, categoryId);
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/remove-user/{categoryId}/{userId}")
    public ResponseEntity<?> removeUserFromCategory(@PathVariable String categoryId,
                                                    @PathVariable String userId) {
        userCategoryService.removeUserFromCategory(userId, categoryId);
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getCategoriesByUser(@PathVariable String userId) {
        return ResponseEntity.ok(userCategoryService.getCategoriesByUserId(userId));
    }


}
