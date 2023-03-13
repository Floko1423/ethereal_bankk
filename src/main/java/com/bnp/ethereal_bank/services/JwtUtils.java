package com.bnp.ethereal_bank.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bnp.ethereal_bank.model.Client;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

//JWT tem 3 partes: Header(type:, alg: ) - Payload(claims(states about an entity +info)-registered/public/private) - Signature( verificar que o sender é  quem é e que nao houve modificacoes na msg)
@Component
public class JwtUtils {

    /*
     * Spring annotation that allows you to inject a value from an external
     * configuration
     * file or environment variable into a class field. In this case, it injects the
     * value of the j
     * wt.secret property from an external configuration file (such as
     * application.properties or
     * pplication.yml) into the secret field of the JwtUtils class.
     * 
     */

    @Value("${jwt.secret}")
    private String secret;
    Client client;

    // tinha generateToken(UserDetails userDetails)a, mudei para Client para poder ir
    // buscar os fields do Client para gerar o token
    public String generateToken(Client client) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nif", client.getnif());
        claims.put("password", client.getpassword());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(client.getName())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody()
                .getSubject();
    }

}
