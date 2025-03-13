package com.backend.springcloud.microservicios.controller;

import com.backend.springcloud.microservicios.entity.Product;
import com.backend.springcloud.microservicios.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Product> buscarProducto(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.fidById(id), HttpStatus.OK);
    }
}
