package com.digitalnx.crm.config.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("${jwt.jwtIssuer")
    private String jwtIssuer;
    final Key key;

    public JwtUtil() {
        this.key = JwtKeyGenerator.getInstance().key;
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(key)
                .compact();
    }

    public String generateAccessToken(User springUser) {
        return Jwts.builder()
                .setSubject(springUser.getUsername())
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .claim("roles", springUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> decoder(String string) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(string);
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JwtKeyGenerator.getInstance().key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}