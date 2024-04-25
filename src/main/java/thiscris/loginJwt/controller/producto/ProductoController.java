package thiscris.loginJwt.controller.producto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiscris.loginJwt.dto.ProductoDTO;
import thiscris.loginJwt.dto.UsuarioDTO;
import thiscris.loginJwt.service.impl.ProductoService;

import java.util.List;

@RestController
@RequestMapping("api-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductoController {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;
    @GetMapping("/producto")
    public List<ProductoDTO> obtenerProducto(){
        return this.productoService.listarProducto();
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId(@PathVariable int id){
        return ResponseEntity.ok(productoService.buscarProductoPorId(id));
    }

    @PostMapping("/producto")
    public ProductoDTO agregarProducto(@RequestBody ProductoDTO producto){
        var productollegada = this.productoService.guardarProducto(producto);
        logger.info("producto a agregar" + producto);
        return productollegada;
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity eliminarProducto(@PathVariable int id){
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
