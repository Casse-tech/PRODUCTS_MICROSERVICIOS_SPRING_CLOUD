package com.backend.springcloud.msvc.products.repository;

import com.backend.lib.mcsv.commons.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findBySku(int id);
}
