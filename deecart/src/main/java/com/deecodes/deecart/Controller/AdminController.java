package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.Category;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.service.CategoryService;
import com.deecodes.deecart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    private String getProductList(Model model){

        model.addAttribute("productList",productService.getProductList());
       return "products";
    }

    @GetMapping("/product/add")
    public String showAddProduct(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("categories",categoryService.getCategoryList());

        return "addProduct";

    }

    @PostMapping("/product/add")
    private String addProduct(@ModelAttribute("product") Product product, @RequestParam("productImage")MultipartFile file,
                              @RequestParam("imgName")String imgName) throws IOException {
       Product tempProduct = new Product();
       tempProduct.setId(product.getId());
       tempProduct.setCategory(categoryService.getCategoryById(product.getCategory().getId()));
       tempProduct.setPrice(product.getPrice());
       tempProduct.setName(product.getName());
       tempProduct.setDescription(product.getDescription());
       String imageUUID;
       if(!file.isEmpty()){
           imageUUID =file.getOriginalFilename();
           Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
           System.out.println("sseee here "+fileNameAndPath.toAbsolutePath());
           Files.write(fileNameAndPath, file.getBytes());
       }else{
           imageUUID = imgName;
       }
       tempProduct.setImageName(imageUUID);
         productService.addProduct(tempProduct);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/id/{id}")
    private String removeProduct(@PathVariable Long id){
        Product product = productService.getProductById(id);
        productService.deleteProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/id/{id}")
    public String updateAddProduct(@PathVariable Long id, Model model){
    Product product = productService.getProductById(id);
    Product tempProduct = new Product();
    tempProduct.setId(product.getId());
    tempProduct.setName(product.getName());
    tempProduct.setCategory(product.getCategory());
    tempProduct.setPrice(product.getPrice());
    tempProduct.setDescription(product.getDescription());
    model.addAttribute("product", tempProduct);
    model.addAttribute("categories",categoryService.getCategoryList());
        return "addProduct";
    }

    /* This is category endpoints*/

    @GetMapping("/categories")
    private String getCategories(Model model){
       model.addAttribute("categories",categoryService.getCategoryList()) ;
         return "categories";

    }

    @PostMapping("/category/add")
    private String addCategory(@ModelAttribute("category") Category category){
        Category tempCat = new Category();
        tempCat.setId(category.getId());
        tempCat.setName(category.getName());
         categoryService.addCategory(tempCat);
         return "redirect:/admin/categories";
    }

    @GetMapping("/category/add")
    public String showAddCategory(Model model){
        model.addAttribute("category",new Product());

        return "addCategory";

    }


    @GetMapping("/delete/category/{id}")
    private String removeCategory(@PathVariable Integer id){
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/update/category/id/{id}")
    public String updateAddCategory(@PathVariable Integer id, Model model){
        Category category = categoryService.getCategoryById(id);
        Category tempCategory = new Category();
        tempCategory.setId(category.getId());
        tempCategory.setName(category.getName());
        model.addAttribute("category", tempCategory);
        return "addCategory";
    }
}
