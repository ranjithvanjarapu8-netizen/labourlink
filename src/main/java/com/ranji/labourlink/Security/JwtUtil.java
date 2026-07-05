package com.ranji.labourlink.Security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {

    private static final String SECRET =
            "LabourLinkSecretKeyLabourLinkSecretKey123456";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRATION =
            1000 * 60 * 60 * 24 * 7; // 7 days

    public static String generateToken(String phoneNumber) {

        return Jwts.builder()
                .subject(phoneNumber)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }
    public boolean isTokenValid(String token) {

        try {

            getPhoneNumber(token);

            return true;

        } catch (Exception e) {

            return false;

        }
    }
    public static String getPhoneNumber(String token) {

        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}