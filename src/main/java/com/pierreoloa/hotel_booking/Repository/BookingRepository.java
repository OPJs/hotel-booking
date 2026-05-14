package com.pierreoloa.hotel_booking.Repository;

import com.pierreoloa.hotel_booking.entity.Booking;
import com.pierreoloa.hotel_booking.entity.Booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, Long>{


    List<Booking> findByUserId (Long userid);
    List<Booking> findByRoomId (Long room);
    List<Booking> findByStatus (BookingStatus status);
    List<Booking> findByUserIdAndStatus(Long userId ,BookingStatus status);    



}
