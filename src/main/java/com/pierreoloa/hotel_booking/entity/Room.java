package com.pierreoloa.hotel_booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;    


@Data
@Entity
@Table(name ="rooms")




public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String Type;

    private String description;

    @Column(nullable = false)
    private BigDecimal priceNight;


    private Integer capacity;
    
    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List <Booking> bookings;




}
