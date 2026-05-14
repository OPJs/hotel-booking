package com.pierreoloa.hotel_booking.controller;

import com.pierreoloa.hotel_booking.dto.AuthResponse;
import com.pierreoloa.hotel_booking.dto.LoginRequest;
import com.pierreoloa.hotel_booking.dto.RegisterRequest;
import com.pierreoloa.hotel_booking.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthService authservice;

    public AuthController(AuthService authService) {

        this.authservice = authService;

    }



    @PostMapping("/register")
    public ResponseEntity <AuthResponse> Ajout (@RequestBody RegisterRequest Ajouteruser) {

            return ResponseEntity.ok(authservice.Register(Ajouteruser));

    }

    @PostMapping("/login")
    public ResponseEntity <AuthResponse> Login (@RequestBody LoginRequest loginUser) {


        return ResponseEntity.ok(authservice.Login(loginUser));
    }

    

}
