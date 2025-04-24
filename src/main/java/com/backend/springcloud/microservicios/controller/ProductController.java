package com.backend.springcloud.microservicios.controller;

import com.backend.springcloud.microservicios.entity.Product;
import com.backend.springcloud.microservicios.exception.ResourceNotFoundException;
import com.backend.springcloud.microservicios.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/products")
@CrossOrigin(originPatterns = "*")
public class ProductController {

    private final IProductoService productoService;

    public ProductController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listarProductos() {
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> buscarProducto(@PathVariable Long id) throws InterruptedException {
        if (id.equals(10L)) {
            throw new IllegalStateException("Producto no encontrado, for testing exception");
        }
        if (id.equals(7L)) {
            TimeUnit.SECONDS.sleep(2L);
        }

        return new ResponseEntity<>(productoService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> crearProducto(@RequestBody Product product) {
        return new ResponseEntity<>(productoService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> actualizarProducto(@RequestBody Product product, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(productoService.update(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) throws ResourceNotFoundException {
        productoService.delete(id);
        return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}