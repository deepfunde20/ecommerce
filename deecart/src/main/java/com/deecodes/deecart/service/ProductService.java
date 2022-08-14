package com.deecodes.deecart.service;

import com.deecodes.deecart.entity.Category;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public Product getProductById(Long id) {
      return  productRepository.findById(id).get();
    }

    public Product updateProduct(Product tempProduct) {
        return productRepository.save(tempProduct);
    }

    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategory_Id(id);
    }
}
