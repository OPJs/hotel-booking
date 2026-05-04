package com.pierreoloa.hotel_booking.Repository;

import com.pierreoloa.hotel_booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;




public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    List<User> findByName(String name);
    List<User> findByEmailContaining(String email);
    Boolean existsByEmail(String email);
    
}
