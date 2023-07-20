//package com.example.TodoList.Security.Jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//@Slf4j
//public class JwtUtils {
//    @Value("${jwt.secret.key}")
//    private String secretKey;
//    @Value("${jwt.time.expiration}")
//    private Long timeExpiration;
//
//    //OBTENER FIRMA DEL TOKEN
//    public Key getSignatureKey(){
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    //GENERAR TOKEN DE ACCESO
//    public String generateAccesToken(String email){
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + timeExpiration))
//                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // VALIDAR TOKEN DE ACCESO
//    public boolean isTokenValid(String token){
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(getSignatureKey())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//            return true;
//        }catch (Exception e){
//            log.error("Invalid token, error: ".concat(e.getMessage()));
//            return false;
//        }
//    }
//
//    //OBTENER TODOS LOS CLAIMS DEL TOKEN
//    public Claims extractAllClaims(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignatureKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    //OBTENER UN SOLO CLAIM
//    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
//        Claims claims = extractAllClaims(token);
//        return claimsTFunction.apply(claims);
//    }
//
////    OBTENER (EMAIL/USERNAME) DEL TOKEN
//    public String getEmailFromToken(String token){
//        return getClaim(token, Claims::getSubject);
//    }
//
//
//}
