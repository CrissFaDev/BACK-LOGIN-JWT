package thiscris.loginJwt.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    Integer idUsuario;
    String nombre;
    String username;
    String correo;
    String password;


}
