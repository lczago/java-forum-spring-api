package spring.rest.config.security;

import spring.rest.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private Duration expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Users logado = (Users) authentication.getPrincipal();
        Instant hoje = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant dataExpiracao = Instant.now().plus(expiration);

        return Jwts.builder()
                .setIssuer("API do fórum da Alura")
                .setSubject(logado.getId().toString())
                .setIssuedAt(Date.from(hoje))
                .setExpiration(Date.from(dataExpiracao))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdusuario(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
