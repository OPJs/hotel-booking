package com.pierreoloa.hotel_booking.services;


import com.pierreoloa.hotel_booking.entity.User;
import com.pierreoloa.hotel_booking.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }


    public User createUser(User user){ 


            if (userRepository.existsByEmail(user.getEmail())) {
                throw new RuntimeException("Email déjà utilisé");
            }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public User getUserById (Long userid){

        return userRepository.findById(userid).orElseThrow(()-> new RuntimeException("Utilisateur Inexistant"));

    }


    public User getUserByEmail (String email){

        return userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("L'utilisateur Inexistant"));
        
    }

    public List <User> getAllUser (Long userid){

        return userRepository.findAll();
    }






}
