package com.lurodev.ApiGestionInspecciones.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "an2u8ouUuPscExx54L9sdfASw51658asdPASDKASDI0541"; //DEBE CUMPLIR CON EL MINIMO DE SEGURIDAD PARA ESTE TIPO DE CLAVES, SI NO ARROJA ERROR
    private static final Integer DAYS_VALIDITY_TOKEN = 30;
    private static final Integer HOURS_VALIDITY_TOKEN = 24 * DAYS_VALIDITY_TOKEN;
    private static final Integer MINUTES_VALIDITY_TOKEN = 60 * HOURS_VALIDITY_TOKEN;
    private static final Long SECONDS_VALIDITY_TOKEN = MINUTES_VALIDITY_TOKEN * 60L;
    private static final Long TIME_VALIDITY_TOKEN = 1000L * SECONDS_VALIDITY_TOKEN; //valor en milisegundos

    public String extractUsername(String jwtToken){
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims (String jwtToken) throws ExpiredJwtException{
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        }catch (ExpiredJwtException jwtExpired){
            return null;
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIME_VALIDITY_TOKEN))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String jwtToken, UserDetails userDetails){
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }
}
