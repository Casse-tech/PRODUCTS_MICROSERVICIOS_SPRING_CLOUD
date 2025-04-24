package com.backend.springcloud.microservicios.service;

import com.backend.springcloud.microservicios.entity.Product;
import com.backend.springcloud.microservicios.exception.ResourceNotFoundException;

import java.util.List;

public interface IProductoService {
    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Product product, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
