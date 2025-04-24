package com.backend.springcloud.microservicios.service.imp;

import com.backend.springcloud.microservicios.entity.Product;
import com.backend.springcloud.microservicios.exception.ResourceNotFoundException;
import com.backend.springcloud.microservicios.repository.ProductRepository;
import com.backend.springcloud.microservicios.service.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoServiceImpl.class);

    private final ProductRepository productRepository;
    private final Environment environment;

    public ProductoServiceImpl(ProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<Product> products = ((List<Product>) productRepository.findAll())
                .stream()
                .map(product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return product;
                }).collect(Collectors.toList());

        System.out.println("products = " + products);
        return products;

    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {

        Product productBuscado = productRepository.findById(id)
                .map(product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return product;
                })
                .orElseThrow(() -> new IllegalArgumentException("No hay datos con id: " + id));

        System.out.println("productBuscado = " + productBuscado);
        return productBuscado;

    }

    @Override
    @Transactional
    public Product save(Product product) {
        productRepository.findBySku(product.getSku())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("El producto ya existe, verificar sku: " + product.getSku());
                });
        LOGGER.info("Guardando producto: " + product);

        product.setCreateAt(LocalDate.now());
        product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        Product productoGuardado = productRepository.save(product);
        LOGGER.info("Producto guardado: " + productoGuardado);

        return  productoGuardado;
    }

    @Override
    @Transactional
    public Product update(Product product, Long id) throws ResourceNotFoundException {
        LOGGER.info("Actualizando producto: " + product);
        LOGGER.info("Id producto: " + id);
        Product productBuscado = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado, verificar id: " + id));

        productBuscado.setName(product.getName());
        productBuscado.setPrice(product.getPrice());
        productBuscado.setCreateAt(product.getCreateAt());
        productBuscado.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        Product productoGuardado = productRepository.save(productBuscado);
        LOGGER.info("Producto guardado: " + productoGuardado);

        return productoGuardado;
    }

    @Override
    @Transactional
    public void delete(Long id) throws ResourceNotFoundException {
        LOGGER.info("Eliminando producto con id: " + id);
        Product productBuscado = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado, verificar id: " + id));

        productRepository.deleteById(id);
        LOGGER.info("Producto eliminado: " + productBuscado);

    }
}
