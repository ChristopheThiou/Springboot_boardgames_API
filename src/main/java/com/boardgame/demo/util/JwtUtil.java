package com.boardgame.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {
    private final String secret = "my-secret-key";

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000000 * 60 * 60))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, String email) {
        return extractClaims(token).getSubject().equals(email) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
        }
}
