package com.pierreoloa.hotel_booking.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;



@Data
@Entity
@Table(name ="bookings")



public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private LocalDate checkIn;
        
        
        
        @Column(nullable = false)
        private LocalDate checkOut;


        @Column(nullable = false)
        private BigDecimal totalPrice;


        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private BookingStatus status = BookingStatus.PENDING;




        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;




        @ManyToOne
        @JoinColumn(name = "room_id", nullable = false)
        private Room room;

        public enum BookingStatus {

            PENDING,
            CONFIRMED,
            CANCELLED
        }


    
}
