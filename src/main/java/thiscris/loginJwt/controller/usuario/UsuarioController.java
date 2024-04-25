package thiscris.loginJwt.controller.usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiscris.loginJwt.dto.UsuarioDTO;
import thiscris.loginJwt.service.impl.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("api-app")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioController {

    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/usuario")
    public List<UsuarioDTO> obtenerUsuario(){
        return this.usuarioService.listarUsuario();
    }

    @PostMapping("/usuario")
    public UsuarioDTO agregarUsuario(@RequestBody UsuarioDTO usuario){
        var usuariollegada = this.usuarioService.guardarUsuario(usuario);
        logger.info("rol a agregar" + usuario);
        return usuariollegada;
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable int id){
       return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable int id){
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

