package com.deecodes.deecart.repository;

import com.deecodes.deecart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


    Cart findCartByUserId(long id);
}
