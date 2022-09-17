package com.deecodes.deecart.repository;

import com.deecodes.deecart.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyOrderRepository extends JpaRepository <MyOrder,Integer> {
}
