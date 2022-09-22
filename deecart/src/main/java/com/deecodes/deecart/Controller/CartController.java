package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.Cart;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.global.GlobalData;
import com.deecodes.deecart.service.CartService;
import com.deecodes.deecart.service.CustomUserDetailsService;
import com.deecodes.deecart.service.ProductService;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @GetMapping("/addToCart/productId/{productId}")
    public String getAddToCart( @PathVariable Long productId, Authentication authentication){

        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
        Product product= productService.getProductById(productId);
        Cart cart = cartService.findCartByUserId(useId);
        if(cart!=null){
           List <Product> productList = cart.getProduct();
           productList.add(product);
           cart.setProduct(productList);
           cartService.saveCart(cart);
        }else{
            List <Product> productList = new ArrayList<>();
            productList.add(product);
            Cart newCart = new Cart();
            newCart.setId(1);
            newCart.setUserId(useId);
            newCart.setProduct(productList);
            newCart.setQuantity(2);
            cartService.saveCart(newCart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cartGet(Model model,Authentication authentication){
        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
        Cart cart = cartService.findCartByUserId(useId);
        if(cart ==null){
            cart = new Cart();
            model.addAttribute("cartCount", 0);
            model.addAttribute("total", 0);
            model.addAttribute("cart", cart);
        }else {
            model.addAttribute("cartCount", cart.getProduct().size());
            model.addAttribute("total", cart.getProduct().stream().mapToDouble(Product::getPrice).sum());
            model.addAttribute("cart", cart);
        }

        return "cart";
    }

        @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index, Authentication authentication){
            long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
            Cart cart = cartService.findCartByUserId(useId);
            List<Product> tempList = cart.getProduct();
     tempList.remove(index);
     cart.setProduct(tempList);
     cartService.saveCart(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart/removeItems")
    public String  emptyCart(Authentication authentication){
        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
        Cart cart = cartService.findCartByUserId(useId);
        List<Product> tempList = cart.getProduct();
        tempList.clear();
        cart.setProduct(tempList);
        cartService.saveCart(cart);
        return "redirect:/shop";
    }


}
