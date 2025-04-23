package com.bridgelabz.employeepayroll.utility;

import com.bridgelabz.employeepayroll.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayroll.model.User;
import com.bridgelabz.employeepayroll.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {
    @Autowired
    UserRepository userRepository;

    private static final String SECRET_KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    // generate token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    // extract email
    public String extractEmail(String token) {
        try {
            System.out.println(token);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("Email: " + claims.getSubject());
            return claims.getSubject();
        } catch (Exception e) {
            throw new EmployeePayrollException("Error: " + e.getMessage());
        }
    }

    // validate token
    public boolean validateToken(String token, String userEmail) {
        final String email = extractEmail(token);
        boolean isToken = true;
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EmployeePayrollException("User not found with email: " + email));
        if(user==null || user.getToken()==null) {
            isToken = false;
        }
        final boolean valid = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().before(new Date());

        return (email.equals(userEmail) && !valid && isToken);
    }
}
