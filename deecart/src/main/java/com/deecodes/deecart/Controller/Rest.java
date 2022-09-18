package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.MyOrder;
import com.deecodes.deecart.service.CustomUserDetailsService;
import com.deecodes.deecart.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Rest {

    @Autowired
    MyOrderService myOrderService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/rest")
    public MyOrder getMyOrder(Authentication authentication){
        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
       List <MyOrder> list =  myOrderService.findByUserID(useId) ;
       return list.get(list.size() - 1);
    }
}
