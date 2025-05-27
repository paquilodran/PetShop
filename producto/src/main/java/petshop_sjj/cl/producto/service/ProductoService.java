package petshop_sjj.cl.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import petshop_sjj.cl.producto.model.Producto;
import petshop_sjj.cl.producto.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> fetchAll() {
        return productoRepository.findAll();
    }

    public Producto fetchById(Integer id) {
        return productoRepository.findAll().get(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}