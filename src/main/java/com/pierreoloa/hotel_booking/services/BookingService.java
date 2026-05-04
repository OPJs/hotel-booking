package com.pierreoloa.hotel_booking.services;

import com.pierreoloa.hotel_booking.entity.Booking;
import com.pierreoloa.hotel_booking.entity.Booking.BookingStatus;
import com.pierreoloa.hotel_booking.entity.Room;
import com.pierreoloa.hotel_booking.entity.User;
import com.pierreoloa.hotel_booking.Repository.BookingRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;



@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final UserService userService;
    public BookingService(BookingRepository bookingRepository, RoomService roomService, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.userService = userService;
    }

    public Booking createBooking (Long roomid, Long userid, Booking booking) {

        Room room = roomService.getRoomById(roomid);
        User user = userService.getUserById(userid);


        if (!room.getAvailable()) {
            throw new RuntimeException("Room is not available for booking");
        }

// calcul du nombre de nuits entre les dates de check-in et check-out
        long night= ChronoUnit.DAYS.between(booking.getCheckin(), booking.getCheckout());


        BigDecimal TotalPrice =  room.getPricePerNight().multiply(BigDecimal.valueOf(night));


        booking.setRoom(room);
        booking.setUser(user);
        booking.setTotalprice(TotalPrice);
        booking.setStatus(BookingStatus.PENDING);


        return bookingRepository.save(booking);

    }
//voir la liste des réservations d'un utilisateur
    public List < Booking> getBookingUser(Long userid){

        return bookingRepository.findByUserId(userid);

    }


    public Booking getBookingById (Long id){

        return bookingRepository.findById(id).orElseThrow(()-> new RuntimeException("Reservation non trouvée"));

    }


    //confirmer une réservation
    public Booking ConfirmerReservation(Long bookingid) {

        Booking booking = getBookingById(bookingid);
        booking.setStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);

    }



// annuler une réservation
    public Booking AnnulerReservation(Long bookingid) {

        Booking booking = getBookingById(bookingid);
        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);

    
}

public List  <Booking> getAllBooking() {

    return bookingRepository.findAll();




}



}
