package com.devdam.server.service.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Retrieves the signing key for JWT token generation.
     * The key is derived from the jwtSecret property.
     *
     * @return a SecretKey used for signing JWT tokens
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(7, ChronoUnit.DAYS))) // 7 days token
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } 
        catch (JwtException | IllegalArgumentException e) {
            log.error("Error extracting username from token: ", e);
            return null; // Token is invalid or malformed
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        final String username = extractUsername(token);

        //return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        if (username == null) {
            return false; // Token is invalid or malformed
        }

        return !isTokenExpired(token);
    }

    @Override
    public boolean validateToken(String token, String role) {
        try {
            String username = extractUsername(token);
            if (username == null) return false;

            // return switch (role.toLowerCase()) {
            //     case "admin" -> adminRepository.existsByUsername(username);
            //     case "doctor" -> doctorRepository.existsByEmail(username);
            //     case "patient" -> patientRepository.existsByEmail(username);
            //     default -> false;
            // };
            return true; // Placeholder, implement actual role validation logic
        } catch (Exception e) {
            log.error("Error validating token: ", e);
            return false;
        }
    }

    @Override
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
}
