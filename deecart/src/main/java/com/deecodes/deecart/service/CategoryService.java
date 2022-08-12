package com.deecodes.deecart.service;

import com.deecodes.deecart.entity.Category;
import com.deecodes.deecart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> getCategoryList() {
      return  categoryRepository.findAll();
    }

    public Category addCategory(Category category) {

        return  categoryRepository.save(category);
    }

    public Category getCategoryById(Integer id) {
       return categoryRepository.findById(id).get();
    }

    public void deleteCategory(Category category){
         categoryRepository.delete(category);
    }
}
