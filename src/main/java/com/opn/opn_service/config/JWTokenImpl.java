package com.opn.opn_service.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.*;
import java.util.Date;



public class JWTokenImpl {

    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048); // 2048-bit RSA
        return generator.generateKeyPair();
    }

//    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.RS256);


//    public static String generateToken(String subject) {
//        Key key = SECRET_KEY;
//        String token = Jwts.builder()
//                .subject(subject) // Updated way to set the subject
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
//                .signWith(key, SignatureAlgorithm.RS256)
//                .compact();
//        return token;
//    }







//    public static String generateToken(String subject) {
//        String token = Jwts.builder()
//                .setSubject(subject)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
//                .signWith(SignatureAlgorithm.RS256, String.valueOf(SECRET_KEY))
//                .compact();
//        return token;
//    }
}
