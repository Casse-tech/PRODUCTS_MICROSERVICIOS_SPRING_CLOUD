package com.backend.springcloud.microservicios.service;

import com.backend.springcloud.microservicios.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Product> findAll();

    Product fidById(Long id);
}
