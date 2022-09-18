package com.deecodes.deecart.service;

import com.deecodes.deecart.entity.MyOrder;
import com.deecodes.deecart.repository.MyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MyOrderService {

    @Autowired
    MyOrderRepository myOrderRepository;

    public MyOrder saveOrder(MyOrder order){
       return myOrderRepository.save(order);
    }

    public MyOrder findByMyOrderId(int id){

       return myOrderRepository.findById(id).get();
    }

    public List<MyOrder>  findByUserID(long id){

        return myOrderRepository.findByUserId(id);
    }

}
