package thiscris.loginJwt.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiscris.loginJwt.dto.UsuarioDTO;
import thiscris.loginJwt.exception.RecursoNoEncontradoException;
import thiscris.loginJwt.model.Usuario;
import thiscris.loginJwt.repository.UsuarioRepository;
import thiscris.loginJwt.service.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<UsuarioDTO> listarUsuario() {
        var usuarios = this.usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(Integer id_usuario) {
        var usurio = this.usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("usuario no encontrado"));

        return modelMapper.map(usurio, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuario) {
        var usuarios = this.usuarioRepository.save(modelMapper.map(usuario, Usuario.class));
        return modelMapper.map(usuarios, UsuarioDTO.class);
    }

    @Override
    public void eliminarUsuario(Integer id_usuario) {
        UsuarioDTO usuario = buscarUsuarioPorId(id_usuario);
        this.usuarioRepository.deleteById(usuario.getIdUsuario());
    }

}
