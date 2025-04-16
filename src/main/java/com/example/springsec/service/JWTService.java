package com.example.springsec.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String userName) {
        System.out.println(SECRET_KEY);
        return Jwts.builder()
                .setSubject(userName) // Replace with dynamic username if needed
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaimas(token).getSubject();
    }

    private Claims extractClaimas(String token) {
        return Jwts.parserBuilder().
                setSigningKey(SECRET_KEY).
                build().
                parseClaimsJws(token).
                getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractClaimas(token).getExpiration().before(new Date());
    }
}
