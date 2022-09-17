package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.MyOrder;
import com.deecodes.deecart.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {

    @Autowired
    MyOrderService myOrderService;

    @GetMapping("/rest")
    public MyOrder getMyOrder(){

       return myOrderService.findByMyOrderId(27);

    }

}
