package com.pierreoloa.hotel_booking.services;
import com.pierreoloa.hotel_booking.entity.Room;
import com.pierreoloa.hotel_booking.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;




@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


    public List <Room> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }


    public List<Room> getAvailableRooms(Long hotelId, LocalDate checkIn, LocalDate checkOut) {

        if (checkIn.isAfter(checkOut) || checkIn.isEqual(checkOut)) {
            throw new RuntimeException("Check-in date must be before check-out date");
        }
    

        if (checkIn.isBefore(LocalDate.now())) {
            throw new RuntimeException("Check-in date cannot be in the past");
        }



        return roomRepository.findAvailableRooms(hotelId, checkIn, checkOut);

    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

  
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setType(roomDetails.getType());
        room.setPriceNight(roomDetails.getPriceNight());
        room.setCapacity(roomDetails.getCapacity());
        room.setDescription(roomDetails.getDescription());
        room.setAvailable(roomDetails.getAvailable());
        return roomRepository.save(room);

    }




    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }



public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        roomRepository.delete(room);
    }



    
}
