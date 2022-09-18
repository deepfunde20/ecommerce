package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.Cart;
import com.deecodes.deecart.entity.MyOrder;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.service.CartService;
import com.deecodes.deecart.service.CustomUserDetailsService;
import com.deecodes.deecart.service.MyOrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.razorpay.*;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    CartService cartService;

    @Autowired
    MyOrderService myOrderService;

    @GetMapping("/create_order")
    public String showAddProduct(Model model, Authentication authentication) throws RazorpayException {

        model.addAttribute("order", new MyOrder());

        int amt;
        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
        Cart cart = cartService.findCartByUserId(useId);
        if (cart == null) {
            amt = 0;
        } else {
            amt = (int) cart.getProduct().stream().mapToDouble(Product::getPrice).sum();

        }

        RazorpayClient client = new RazorpayClient("rzp_test_Hy6zVKGsjwD4is","gWqka5oaCeMhp5tFWORUhLFo");

        JSONObject ob =new JSONObject();
        ob.put("amount",amt*100);
        ob.put("currency","INR");
        ob.put("receipt","txn_546654");
        //creating new order
        Order create = client.Orders.create(ob);
        System.out.println(create);

        String orderId =  create.get("id");
        System.out.println("This is order id "+orderId);
        MyOrder myod = new MyOrder();
        myod.setAmount(amt);
        myod.setCurrency("INR");
        myod.setRazerOrderId(orderId);
        myod.setUserId(useId);
        myOrderService.saveOrder(myod);
        model.addAttribute("orderId",orderId);
        return "payment";

    }
    @GetMapping("/pay")
    public String pay(Model model){
        model.addAttribute("order", myOrderService.findByMyOrderId(33));
        return "payment";
    }
}
