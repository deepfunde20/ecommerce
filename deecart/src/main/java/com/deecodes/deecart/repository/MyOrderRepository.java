package com.deecodes.deecart.repository;

import com.deecodes.deecart.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyOrderRepository extends JpaRepository <MyOrder,Integer> {
   List <MyOrder> findByUserId(long id);
}
