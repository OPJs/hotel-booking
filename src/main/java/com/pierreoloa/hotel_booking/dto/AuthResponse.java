package com.pierreoloa.hotel_booking.dto;
import lombok.Data;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String email;
    private String role;
    
}
