package thiscris.loginJwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import thiscris.loginJwt.model.Usuario;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtTokenProvider {

    // Clave secreta utilizada para firmar y verificar tokens JWT
    private static final String SECRET_KEY = "586E23132156465456E65456456456G564564564G87976541111";

    // Método para generar un token JWT utilizando la información del usuario proporcionada
    public String getTokend(UserDetails user) {
        return getTokend(new HashMap<>(), user);
    }

    // Método sobrecargado para permitir reclamaciones adicionales al generar un token JWT
    private String getTokend(Map<String, String> extraClaims, UserDetails user) {
        // Verifica si el usuario es una instancia de Usuario
        if (user instanceof Usuario) {
            Usuario usuario = (Usuario) user;
            extraClaims.put("rol", usuario.getRol().name()); // Agrega el rol al payload del token
        }
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Expira en 24 horas
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Método para obtener la clave secreta utilizada para firmar y verificar tokens JWT
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Método para verificar si un token JWT está correctamente firmado
    public boolean isTokenSigned(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true; // La firma del token es válida
        } catch (Exception e) {
            return false; // La firma del token es inválida o no existe
        }
    }

    // Método para obtener el nombre de usuario desde un token JWT
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Método para verificar si un token JWT es válido para un usuario proporcionado
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Método para obtener todas las reclamaciones (claims) de un token JWT
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para obtener una reclamación específica de un token JWT
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Método para obtener la fecha de expiración de un token JWT
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    // Método para verificar si un token JWT ha expirado
    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
