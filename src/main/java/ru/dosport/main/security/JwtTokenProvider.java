package ru.dosport.main.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.dosport.main.dto.UserRequest;
import ru.dosport.main.exceptions.JwtAuthenticationException;
import ru.dosport.main.services.api.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static ru.dosport.main.helpers.Messages.JWT_TOKEN_NOT_VALID;

/**
 * Утилитный класс провайдера JWT токенов, генерирующий и валидирующий JWT токены.
 */
@Component
public class JwtTokenProvider {

    @Value("default")
    private String secret;

    // Токен действителен 10 дней
    @Value("864000000")
    private long validityInMilliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, Long id, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", id);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails;
        try {
            userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        } catch (UsernameNotFoundException e) {
            userDetails = createUserDetailsByToken(token);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException(JWT_TOKEN_NOT_VALID);
        }
    }

    private UserDetails createUserDetailsByToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        try {
            UserRequest userRequest = new UserRequest();
            userRequest.setUsername(claims.getSubject());
            userRequest.setAuthorities((List<String>) claims.get("roles"));
            userRequest.setId(((Integer) claims.get("id")).longValue());
            userService.save(userRequest);
            return this.userDetailsService.loadUserByUsername(claims.getSubject());
        } catch (ClassCastException e) {
            throw new JwtAuthenticationException(JWT_TOKEN_NOT_VALID);
        }
    }
}
