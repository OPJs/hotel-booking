package com.pierreoloa.hotel_booking.Repository;


import com.pierreoloa.hotel_booking.entity.Hotel;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



 @Repository   
public interface HotelRepository  extends JpaRepository <Hotel,Long>{
    
List<Hotel> findByCity(String city);
List<Hotel> findByStars(String stars);
List<Hotel> findByName(String name);
List<Hotel> findByCityAndStars(String city, String stars);
//List<Hotel> findByRatingGreaterThanEqual(Double rating);


}
