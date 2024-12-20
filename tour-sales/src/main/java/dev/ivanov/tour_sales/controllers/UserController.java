package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteAccount(@PathVariable String userId) {

        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/by-tour/{tourId}")
    public ResponseEntity<?> getUsersByTour(@PathVariable String tourId) {
        return ResponseEntity.ok(userService.getUsersByTour(tourId));
    }

}
