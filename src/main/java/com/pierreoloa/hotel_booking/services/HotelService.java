package com.pierreoloa.hotel_booking.services;

import com.pierreoloa.hotel_booking.entity.Hotel;
import com.pierreoloa.hotel_booking.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.findByCity(city);
    }


    /*public List<Hotel> getHotelsByRating(Double rating) {
        return hotelRepository.findByRatingGreaterThanEqual(rating);
    }*/

    public List<Hotel> getHotelsByStars(String stars) {
        return hotelRepository.findByStars(stars);
    }

    public List<Hotel> getHotelsByName(String name) {
        return hotelRepository.findByName(name);
    }

    public List<Hotel> getHotelsByCityAndStars(String city, String stars) {
        return hotelRepository.findByCityAndStars(city, stars);
    }

// CRUD operations for Hotel

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }



// Update an existing hotel
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotel.setName(hotelDetails.getName());
        hotel.setCity(hotelDetails.getCity());
        hotel.setStars(hotelDetails.getStars());      
        hotel.setAdress(hotelDetails.getAdress());
        return hotelRepository.save(hotel);  
    }




// Delete a hotel

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
    
}
