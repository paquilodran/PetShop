package petshop_sjj.cl.producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import petshop_sjj.cl.producto.model.Producto;
import petshop_sjj.cl.producto.service.ProductoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.fetchAll();

        if (productos.isEmpty()) {
            // error 404 not found
            return ResponseEntity.notFound().build();
        }
        // STATUS 200 OK
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Integer id) {

        Producto producto = productoService.fetchById(id);

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        Producto productoNuevo = productoService.save(producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Integer id,
            @RequestBody Producto producto) {
        Producto prod = productoService.fetchById(id);

        prod.setId(id);
        prod.setName(producto.getName());
        prod.setPrice(producto.getPrice());
        prod.setStock(producto.getStock());

        productoService.save(prod);
        return ResponseEntity.ok(prod);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
