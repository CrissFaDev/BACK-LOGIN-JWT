package thiscris.loginJwt.service;

import thiscris.loginJwt.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    public List<ProductoDTO> listarProducto();
    public ProductoDTO buscarProductoPorId(Integer id_producto);

    public ProductoDTO guardarProducto(ProductoDTO producto);
    public void eliminarProducto(Integer id_producto);
}
