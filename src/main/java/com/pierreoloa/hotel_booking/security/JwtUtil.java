package com.pierreoloa.hotel_booking.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("{jwt.secret}")
    private String secretkey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    private Key getCleSignature() {

        return Keys.hmacShaKeyFor(secretkey.getBytes());

    }

    public String GenerationToken (String email){

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ expirationTime))
                .signWith(getCleSignature(), SignatureAlgorithm.HS256)
                .compact();

    }


    public String extractEmail (String token) {

        return extractClaims(token).getSubject();

    }

    public Boolean TokenValide (String token, String mail){

        String tokenMail= extractEmail(token);
        return tokenMail.equals(mail) && !isTokenExpired(token);

    } 


    private Boolean isTokenExpired (String Token){

        return extractClaims(Token).getExpiration().before(new Date());


    }



    private Claims extractClaims (String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getCleSignature())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }




    
}
