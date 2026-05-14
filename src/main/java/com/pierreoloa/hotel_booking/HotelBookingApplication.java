package com.pierreoloa.hotel_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.pierreoloa.hotel_booking.entity")
@ComponentScan(basePackages = {
    "com.pierreoloa.hotel_booking",
    "com.pierreoloa.hotel_booking.controller",
    "com.pierreoloa.hotel_booking.services",
    "com.pierreoloa.hotel_booking.security",
    "com.pierreoloa.hotel_booking.Repository"
})
public class HotelBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApplication.class, args);
	}

}
