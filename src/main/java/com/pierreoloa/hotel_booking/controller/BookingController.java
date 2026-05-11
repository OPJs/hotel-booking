package com.pierreoloa.hotel_booking.controller;

import com.pierreoloa.hotel_booking.entity.Booking;
import com.pierreoloa.hotel_booking.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;





@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingservice;

    private BookingController ( BookingService bookingservice) {

        this.bookingservice = bookingservice;
    }


    @PostMapping
    public ResponseEntity <Booking> createBooking (@RequestBody Booking booking, @RequestParam Long roomid, @RequestParam Long userid ){

        Booking created = bookingservice.createBooking(roomid, userid, booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }



    @GetMapping("/user/{userId}")
    public ResponseEntity <List <Booking>> getallBookingByUserid (@PathVariable Long userid) {

        return ResponseEntity.ok(bookingservice.getBookingUser(userid));

    }


    @GetMapping("/{id}")
    public ResponseEntity <Booking> getBookingById (@PathVariable Long id) {

            return ResponseEntity.ok(bookingservice.getBookingById(id));


    }

@PutMapping("{id}/confirm")
public ResponseEntity <Booking> ConfirmBooking (@PathVariable Long id) {

    return ResponseEntity.ok(bookingservice.ConfirmerReservation(id));

}


@PutMapping("{id}/cancel")
public ResponseEntity <Booking> CancelBooking (@PathVariable Long id){

    return ResponseEntity.ok(bookingservice.AnnulerReservation(id));


}



// afficher toutes les réservations
@GetMapping
public ResponseEntity <List <Booking>> getAllBooking(){


return ResponseEntity.ok(bookingservice.getAllBooking());


}


    
}
