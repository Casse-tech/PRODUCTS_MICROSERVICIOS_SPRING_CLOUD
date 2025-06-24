package com.backend.springcloud.msvc.products.controller;

import com.backend.lib.mcsv.commons.entity.Product;
import com.backend.springcloud.msvc.products.exception.ResourceNotFoundException;
import com.backend.springcloud.msvc.products.service.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/products")
@CrossOrigin(originPatterns = "*")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final IProductoService productoService;

    public ProductController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listarProductos(@RequestHeader(name="message-request", required = false) String messageRequest) {
        LOGGER.info("llamada al metodo del controller ProductController::listarProductos");
        LOGGER.info("message-request: {}", messageRequest);
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> buscarProducto(@PathVariable Long id) throws InterruptedException {
        LOGGER.info("llamada al metodo del controller ProductController::buscarProducto");
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
        LOGGER.info("llamada al metodo del controller ProductController::crearProducto {}",product);
        return new ResponseEntity<>(productoService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> actualizarProducto(@RequestBody Product product, @PathVariable Long id) throws ResourceNotFoundException {
        LOGGER.info("llamada al metodo del controller ProductController::actualizarProducto {}",product);
        return new ResponseEntity<>(productoService.update(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) throws ResourceNotFoundException {
        productoService.delete(id);
        LOGGER.info("llamada al metodo del controller ProductController::eliminarProducto {}",id);
        return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}