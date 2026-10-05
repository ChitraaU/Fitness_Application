package com.project.fitness.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    private String secret = "YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    private int jwtExpirationInMs = 172800000 ;

    public String getJwtfromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    
    public String generateToken(String userId,String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of((role)))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .signWith(key())
                .compact();

    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build()
                    .parseSignedClaims(token);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }


    public String getUserIDfromTokens(String jwt){
        return Jwts.parser().verifyWith((SecretKey) key()).build()
                .parseSignedClaims(jwt).
                getPayload().getSubject();
    }
    
    
    public Claims getAllClaims(String jwt){
        return Jwts.parser().verifyWith((SecretKey) key()).build()
                .parseSignedClaims(jwt).
                getPayload();
    }
}
