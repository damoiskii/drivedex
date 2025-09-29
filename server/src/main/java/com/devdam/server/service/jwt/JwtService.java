package com.devdam.server.service.jwt;

import java.util.Date;

public interface JwtService {
    String generateToken(String username);
    String extractUsername(String token);
    boolean isTokenValid(String token);
    boolean validateToken(String token, String role);
    Date extractExpiration(String token);
}
