package dev.ivanov.tour_sales.controllers;

import dev.ivanov.tour_sales.dto.auth.LoginDto;
import dev.ivanov.tour_sales.dto.auth.RegisterDto;
import dev.ivanov.tour_sales.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        String jwt = authService.login(loginDto);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        String jwt = authService.register(registerDto);
        return ResponseEntity.ok(jwt);
    }

}
