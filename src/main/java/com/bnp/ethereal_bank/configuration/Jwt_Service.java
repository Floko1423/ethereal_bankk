package com.bnp.ethereal_bank.configuration;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


//@Service
public class Jwt_Service {

//     private static final String =""; // min 256 bit-> mais bits maior seguranca

    
    public String extractClientName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(getSignInkey()) // set da key fazer decode do toke -> secret para fazer sign do jwt -> usado para verificar o sender
        .build().parseClaimsJws(token)
        .getBody(); 
    }

    private Key getSignInkey() {
        byte [] keyBytes = Decoders..decode();

        return Keys.hmacShaKeyFor(keyBytes); // alg
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 

        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) { //

        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
        .signWith(getSignInkey(), SignatureAlgorithm.HS256)
        .compact(); // gera e devolve o token com os claims dados e os userDetails do user
    }

    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);

    }

    
    public boolean isTokenValid(String token, UserDetails userDetails) {//metodo para validar token

        final String username = extractClientName(token);

        return(username.equals(userDetails.getUsername())) && isTokenExpired(token);


    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }


    // public String generateToken(Client client) {
    //     Map<String, Object> claims = new HashMap<>();
    //     claims.put("nif", client.getnif());
    //     claims.put("password", client.getpassword());
    //     return Jwts.builder()
    //             .setClaims(claims)
    //             .setSubject(client.getName())
    //             .setIssuedAt(new Date())
    //             .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
    //             .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
    //             .compact();
    // }

}
