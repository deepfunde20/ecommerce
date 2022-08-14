package com.deecodes.deecart.repository;

import com.deecodes.deecart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByCategory_Id(Integer id);
}
