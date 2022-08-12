package com.deecodes.deecart.repository;

import com.deecodes.deecart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
