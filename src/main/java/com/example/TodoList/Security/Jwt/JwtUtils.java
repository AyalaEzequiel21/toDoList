package com.example.TodoList.Security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String Key;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    @Value("${jwt.app.jwtCookieName}")
    private String jwtCookie;

    Key secureKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Get Signature
//    public Key getSignatureKey(){
//        byte[] keyBytes = Decoders.BASE64.decode(secureKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    //Generate Access JWT
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(secureKey, SignatureAlgorithm.HS256)
                .compact();
    }

    //Validate JWT
    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secureKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e){
            log.error("Token invalid, error: " + e.getMessage());
            return  false;
        }
    }

    //Get all claims from token
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secureKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Get specific claim
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    //Get Username / Email
    public String getUserNameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    //Get Cookie from JWT
    public String getJwtCookies(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null){
            return  cookie.getValue();
        } else return null;
    }

    //Generate Cookie with JWT
    public ResponseCookie generateJwtCookie(UserDetails userDetails){
        String jwt = generateToken(userDetails.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24*60*60).httpOnly(true).build();
        return cookie;
    }

    //Clean Cookie in Logout
    public ResponseCookie getCleanJwtCookie(){
        return ResponseCookie.from(jwtCookie, null).path("/api").build();
    }


}
