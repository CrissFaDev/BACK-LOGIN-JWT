package thiscris.loginJwt.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String username;
    private String correo;
    private String password;
}
