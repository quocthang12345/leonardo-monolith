package com.leonardo.util;

import com.leonardo.security.UserPrincipal;
import com.leonardo.service.impl.CustomUserDetail;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JwtTokenProvider {

    // IMPORTANT: In production, move this to application.properties
    // The secret must be at least 64 characters (512 bits) for HS512
    private final String JWT_SECRET = "4e615267556b58703273357638792f423f4528482b4d6251655468576d5a7134";
    private final long JWT_EXPIRATION = 604800000L;

    /**
     * Converts the String secret into a secure SecretKey object.
     * Use Base64 decoding if your secret is encoded, or getBytes() for raw strings.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = JWT_SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(CustomUserDetail userDetails) {
        return createToken(userDetails.getId());
    }

    public String generateToken(UserPrincipal userPrincipal) {
        return createToken(userPrincipal.getId());
    }

    private String createToken(Long subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .subject(String.valueOf(subject))
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey(), Jwts.SIG.HS512) // Modern signWith syntax
                .compact();
    }

    public String getIdFromJWT(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // verifyWith replaces setSigningKey
                .build()
                .parseSignedClaims(token)     // parseSignedClaims replaces parseClaimsJws
                .getPayload()                // getPayload replaces getBody
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}