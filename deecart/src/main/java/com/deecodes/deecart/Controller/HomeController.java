package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.Category;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.global.GlobalData;
import com.deecodes.deecart.service.CategoryService;
import com.deecodes.deecart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("userId")
public class HomeController {


    @Autowired
  ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/shop")
    public String getProduct(Model model){
        model.addAttribute("productList",productService.getProductList());
        model.addAttribute("categories", categoryService.getCategoryList());
        model.addAttribute("cartCount", GlobalData.cart.size());

        return "shop";
    }

    @GetMapping("shop/category/{id}")
    public String productByCategory(@PathVariable Integer id, Model model){
        model.addAttribute("productList", productService.getAllProductsByCategoryId(id));
        model.addAttribute("categories", categoryService.getCategoryList());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/viewProduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }
}
