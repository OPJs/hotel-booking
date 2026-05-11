package com.pierreoloa.hotel_booking.controller;

import com.pierreoloa.hotel_booking.services.HotelService;
import com.pierreoloa.hotel_booking.entity.Hotel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;





@RestController

@RequestMapping("/api/hotels")


public class HotelController {


        private final HotelService hotelService;

        public HotelController (HotelService hotelService) {


            this.hotelService = hotelService;
        }
        

        @GetMapping
        public ResponseEntity <List<Hotel>> getAllHotels() {

            return ResponseEntity.ok(hotelService.getAllHotels());
            
        }



        @GetMapping("/{id}")
        public ResponseEntity <Hotel> getHotelById (@PathVariable Long id) {

            return ResponseEntity.ok(hotelService.getHotelById(id));
            
        }


        public ResponseEntity <List<Hotel>> searchHotels (@RequestParam String city, @RequestParam (required = false) String stars) {

            if (stars != null) {

                return ResponseEntity.ok(hotelService.getHotelsByCityAndStars(city, stars));

            } else {

                return ResponseEntity.ok(hotelService.getHotelsByCity(city));

            }
            
        }
    


        @PostMapping
        public ResponseEntity <Hotel> creaEntityteHotel (@RequestBody Hotel hotel) {

            Hotel createHotel = hotelService.saveHotel(hotel);

            return ResponseEntity.status(HttpStatus.CREATED).body(createHotel);
            
        }

        @PutMapping("/{id}")
        public ResponseEntity <Hotel> updateEntityHotel (@PathVariable Long id, @RequestBody Hotel hotelDetails) {


            return ResponseEntity.ok(hotelService.updateHotel(id, hotelDetails));
        } 


        @DeleteMapping("/{id}")
        public ResponseEntity < Void> deleteEntityHotel (@PathVariable Long id) {

            hotelService.deleteHotel(id);

            return ResponseEntity.noContent().build();
        }
        





}
