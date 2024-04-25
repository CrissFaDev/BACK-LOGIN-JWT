package thiscris.loginJwt.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiscris.loginJwt.dto.ProductoDTO;
import thiscris.loginJwt.exception.RecursoNoEncontradoException;
import thiscris.loginJwt.model.Producto;
import thiscris.loginJwt.repository.ProductoRepository;
import thiscris.loginJwt.service.IProductoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> listarProducto() {
        var productos = this.productoRepository.findAll();
        return productos.stream().map(producto -> this.modelMapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO buscarProductoPorId(Integer id_producto) {
        var producto = this.productoRepository.findById(id_producto)
                .orElseThrow(() -> new RecursoNoEncontradoException("producto no encontrado"));
        return this.modelMapper.map(producto, ProductoDTO.class);

    }

    @Override
    public ProductoDTO guardarProducto(ProductoDTO producto) {
        var productoagregar = this.productoRepository.save(modelMapper.map(producto, Producto.class));
        return modelMapper.map(productoagregar, ProductoDTO.class);
    }

    @Override
    public void eliminarProducto(Integer id_producto) {
        ProductoDTO productodb = buscarProductoPorId(id_producto);
        this.productoRepository.deleteById(productodb.getIdProducto());
    }
}
