package thiscris.loginJwt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thiscris.loginJwt.model.Role;
import thiscris.loginJwt.model.Usuario;
import thiscris.loginJwt.repository.UsuarioRepository;
import thiscris.loginJwt.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtTokenProvider.getTokend(user);
        var validacion = jwtTokenProvider.isTokenSigned(token);
        System.out.println(validacion);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario user = Usuario.builder()
                .nombre(request.getNombre())
                .username(request.getUsername())
                .correo(request.getCorreo())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Role.USER)
                .build();

        usuarioRepository.save(user);


        return AuthResponse.builder()
                .token(jwtTokenProvider.getTokend(user))
                .build();
    }
}
