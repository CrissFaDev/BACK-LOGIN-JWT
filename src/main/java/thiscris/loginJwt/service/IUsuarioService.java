package thiscris.loginJwt.service;

import thiscris.loginJwt.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    public List<UsuarioDTO> listarUsuario();

    public UsuarioDTO buscarUsuarioPorId(Integer id_usuario);

    public UsuarioDTO guardarUsuario(UsuarioDTO usuario);
    public void eliminarUsuario(Integer id_usuario);
}
