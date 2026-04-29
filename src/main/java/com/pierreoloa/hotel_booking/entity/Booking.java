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
        private LocalDate checkin;
        
        
        
        @Column(nullable = false)
        private LocalDate checkout;


        @Column(nullable = false)
        private BigDecimal totalprice;


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
