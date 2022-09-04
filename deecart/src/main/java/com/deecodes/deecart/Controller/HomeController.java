package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.Cart;
import com.deecodes.deecart.entity.Category;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.global.GlobalData;
import com.deecodes.deecart.service.CartService;
import com.deecodes.deecart.service.CategoryService;
import com.deecodes.deecart.service.CustomUserDetailsService;
import com.deecodes.deecart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CartService cartService;

    @GetMapping("/shop")
    public String getProduct(Model model, Authentication authentication){
        model.addAttribute("productList",productService.getProductList());
        model.addAttribute("categories", categoryService.getCategoryList());
        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
        Cart cart = cartService.findCartByUserId(useId);
        if(cart ==null){
             cart = new Cart();
            model.addAttribute("cartCount", 0);
        }else {
            model.addAttribute("cartCount", cart.getProduct().size());
        }

        return "shop";
    }

    @GetMapping("shop/category/{id}")
    public String productByCategory(@PathVariable Integer id, Model model, Authentication authentication){
        model.addAttribute("productList", productService.getAllProductsByCategoryId(id));
        model.addAttribute("categories", categoryService.getCategoryList());
        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
        Cart cart = cartService.findCartByUserId(useId);
        if(cart ==null){
            cart = new Cart();
            model.addAttribute("cartCount", 0);
        }else {
            model.addAttribute("cartCount", cart.getProduct().size());
        }

        return "shop";
    }

    @GetMapping("/shop/viewProduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model, Authentication authentication){
        model.addAttribute("product", productService.getProductById(id));
//        model.addAttribute("cartCount", GlobalData.cart.size());
        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
        Cart cart = cartService.findCartByUserId(useId);
        if(cart ==null){
            cart = new Cart();
            model.addAttribute("cartCount", 0);
        }else {
            model.addAttribute("cartCount", cart.getProduct().size());
        }
        return "viewProduct";
    }
}
