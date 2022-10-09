package cl.challenge.tenpo.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UserDetails mainUser = (UserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("token mal formado {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("token no soportado {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("token expirado {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("token vacío {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("fail en la firma {}", e.getMessage());
        }
        return false;
    }
}
