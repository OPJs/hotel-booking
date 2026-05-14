package com.pierreoloa.hotel_booking.Repository;

import com.pierreoloa.hotel_booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;





@Repository
public interface RoomRepository extends JpaRepository <Room, Long>{
    
        List <Room> findByHotelId(Long hotelid);
        List <Room> findByAvailable (Boolean available);
        @Query( """
            
            SELECT  r FROM Room r
            where r.hotel.id = :hotelid
            and r.available = true 
            and r.id not in (select b.room.id from Booking b where b.status != 'CANCELLED' and b.checkIn < :checkOut and b.checkOut > :checkIn )

          """)

         List<Room> findAvailableRooms(
        @Param("hotelId") Long hotelId,
        @Param("checkIn") LocalDate checkIn,
        @Param("checkOut") LocalDate checkOut
    ); 

}
