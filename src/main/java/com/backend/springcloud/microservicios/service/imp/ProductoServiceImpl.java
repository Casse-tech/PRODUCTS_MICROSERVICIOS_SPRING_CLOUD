package com.backend.springcloud.microservicios.service.imp;

import com.backend.springcloud.microservicios.entity.Product;
import com.backend.springcloud.microservicios.repository.ProductRepository;
import com.backend.springcloud.microservicios.service.IProductoService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {

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
    public Product fidById(Long id) {

        Product productBuscado = productRepository.findById(id)
                .map(product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return product;
                })
                .orElseThrow(() -> new IllegalArgumentException("No hay datos con id: " + id));

        System.out.println("productBuscado = " + productBuscado);
        return productBuscado;

    }
}
