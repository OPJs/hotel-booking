package com.pierreoloa.hotel_booking.controller;


import com.pierreoloa.hotel_booking.entity.Room;
import com.pierreoloa.hotel_booking.services.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("api/rooms/")

public class RoomController {


    private final RoomService roomservice;


    //constructeur de classe qui permet d(utiliser les méthodes de la classe RoomService dans la classe RoomController)
    public RoomController ( RoomService roomservice) {

        this.roomservice = roomservice;
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity <List<Room>> getRoomByHotel (@PathVariable Long hotelid) {

        return ResponseEntity.ok (roomservice.getRoomsByHotel(hotelid));

    }

    @GetMapping("/{id}")
    public  ResponseEntity <Room> getRoomByid (@PathVariable Long id) {

        return ResponseEntity.ok( roomservice.getRoomById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity <List <Room>>getavailableRooms( @RequestParam Long id, @RequestParam @DateTimeFormat (iso =  DateTimeFormat.ISO.DATE) LocalDate checkIn, @RequestParam @DateTimeFormat (iso =  DateTimeFormat.ISO.DATE) LocalDate checkOut) {

        return ResponseEntity.ok(roomservice.getAvailableRooms(id, checkIn, checkOut));

    } 




    @PostMapping
    public ResponseEntity <Room>  createRoom (@RequestBody Room roomadd) {
        return ResponseEntity.status (HttpStatus.CREATED).body(roomservice.saveRoom(roomadd));
    }
    

    @PutMapping("/{id}")
    public ResponseEntity <Room> updateRoom (@RequestBody Room room, @PathVariable Long id ){

        this.roomservice.updateRoom(id, room);
        return ResponseEntity.ok(room);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity <Void> DeleteRoom (@PathVariable Long id){

        roomservice.deleteRoom(id);
        return ResponseEntity.noContent().build();

    }




    
    
}
