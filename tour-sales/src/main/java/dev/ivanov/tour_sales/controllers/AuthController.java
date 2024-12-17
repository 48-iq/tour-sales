package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.auth.LoginDto;
import dev.ivanov.tour_sales.dto.auth.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        System.out.println(1);
        return null;
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount() {
        return null;
    }
}
