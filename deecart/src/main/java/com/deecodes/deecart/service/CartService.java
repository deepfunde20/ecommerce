package com.deecodes.deecart.service;

import com.deecodes.deecart.entity.Cart;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    Cart cart = new Cart();
    @Autowired
    CartRepository cartRepository;

    public List<Product> addToCart(Product product, int quantity){

        List <Product> list = new ArrayList<>();
        list.add(product);
        cart.setProduct(list);
        cart.setQuantity(quantity);
        cartRepository.save(cart);

        return list;

    }

    public List<Cart> getCart(){
       return cartRepository.findAll();
    }

    public Cart findCartByUserId(long id) {

    return  cartRepository.findCartByUserId(id);
    }

    public Cart saveCart(Cart cart){
       return cartRepository.save(cart);
    }
}
