package com.adopt.adopt.Security.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${adopt.app.jwtSecret}")
    private String jwtSecret;

    @Value("${adopt.app.jwtExpireMs}")
    private int jwtExpireMs;

    public String generateJwtToken(String username, String password) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration((new Date(System.currentTimeMillis() + jwtExpireMs )))
                .and()
                .signWith(getKey())
                .compact();
    }

    public long getJwtExpirationDate() { //TODO: Remove conversion as it is in milliseconds
        return TimeUnit.MILLISECONDS.toMillis(jwtExpireMs);
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsername(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);

        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Malformed JWT");

        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "Expired JWT");

        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("Unsupported JWT");

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Illegal Argument JWT");
        }

    }

    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    private Date getExpireDateFromToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpireDateFromToken(token);
        return expiration.before(new Date());
    }

}
