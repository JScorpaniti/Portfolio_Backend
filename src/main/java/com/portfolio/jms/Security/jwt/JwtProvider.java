 package com.portfolio.jms.Security.jwt;

import com.portfolio.jms.Security.Entity.UsuarioPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class JwtProvider{
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date()
                        .getTime()+expiration*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    
    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token) {
    try {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    } catch (MalformedJwtException e) {
        logger.error("Token mal formado");
        throw new IllegalArgumentException("Token mal formado", e);
    } catch (UnsupportedJwtException e) {
        logger.error("Token no soportado");
        throw new IllegalArgumentException("Token no soportado", e);
    } catch (ExpiredJwtException e) {
        logger.error("Token expirado");
        throw new IllegalArgumentException("Token expirado", e);
    } catch (IllegalArgumentException e) {
        logger.error("Token vacio");
        throw new IllegalArgumentException("Token vacio", e);
    } catch (SignatureException e) {
        logger.error("Firma no válida");
        throw new IllegalArgumentException("Firma no válida", e);
    }
    }
} 