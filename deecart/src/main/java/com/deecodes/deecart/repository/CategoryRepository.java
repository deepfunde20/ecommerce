package com.deecodes.deecart.repository;

import com.deecodes.deecart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


  public  Category getCategoryByName(String name);
}
