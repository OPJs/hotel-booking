package com.pierreoloa.hotel_booking.services;

import com.pierreoloa.hotel_booking.dto.AuthResponse;
import com.pierreoloa.hotel_booking.dto.LoginRequest;
import com.pierreoloa.hotel_booking.dto.RegisterRequest;
import com.pierreoloa.hotel_booking.entity.User;
import com.pierreoloa.hotel_booking.Repository.UserRepository;
import com.pierreoloa.hotel_booking.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {


        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtUtil jwutil;
        private final AuthenticationManager authenticationManager;

        public AuthService (UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtutil, AuthenticationManager authenticationManager) {

            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.jwutil = jwtutil;
            this.authenticationManager = authenticationManager;


        }





        public AuthResponse Register (RegisterRequest AjouterUser) {
            

            if (userRepository.existsByEmail(AjouterUser.getEmail())) {
                throw new RuntimeException("Email already exists");
            }

            User user = new User();
            user.setEmail(AjouterUser.getEmail());
            user.setFirstName(AjouterUser.getFirstName());
            user.setLastName(AjouterUser.getLastName());
            user.setPhone(AjouterUser.getPhone());
            user.setPassword(passwordEncoder.encode(AjouterUser.getPassword()));
            user.setRole(User.Role.USER);
            userRepository.save(user);    

            String token = jwutil.GenerationToken(user.getEmail());

            return new AuthResponse(token, user.getEmail(), user.getRole().name());

        }



        public AuthResponse Login (LoginRequest login) {


                authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

                User  user = userRepository.findByEmail(login.getEmail()).orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

                String token = jwutil.GenerationToken(user.getEmail());


                return new AuthResponse(token, user.getEmail(), user.getRole().name());


        }
    
    
    
    }

