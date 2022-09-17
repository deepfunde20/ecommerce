package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.MyOrder;
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

    @PostMapping(value = "/create_order")
    public String createOrder(@ModelAttribute("order") MyOrder myod, Model model) throws RazorpayException {
        int amt = myod.getAmount();  ;
        RazorpayClient client = new RazorpayClient("rzp_test_Hy6zVKGsjwD4is","gWqka5oaCeMhp5tFWORUhLFo");


        JSONObject ob =new JSONObject();
        ob.put("amount",amt*100);
        ob.put("currency",myod.getCurrency());
        ob.put("receipt","txn_546654");
        //creating new order
        Order create = client.Orders.create(ob);
        System.out.println(create);

      String orderId =  create.get("id");
        System.out.println("This is order id "+orderId);
        myod.setRazerOrderId(orderId);
        myOrderService.saveOrder(myod);

        model.addAttribute("orderId",orderId);
        return "shop";
    }

    @GetMapping("/create_order")
    public String showAddProduct(Model model, Authentication authentication){

        model.addAttribute("order", new MyOrder());
//        model.addAttribute("order", 34);
//        long useId = customUserDetailsService.findByUserName(authentication.getName()).getId();
//        Cart cart = cartService.findCartByUserId(useId);
//        if(cart ==null){
//            cart = new Cart();
//            model.addAttribute("amount", 0);
//        }else {
//            model.addAttribute("amount", cart.getProduct().stream().mapToDouble(Product::getPrice).sum());
//        }
        return "orders";

    }

    @GetMapping("/pay")
    public String pay(Model model){
        model.addAttribute("order", myOrderService.findByMyOrderId(23));
        return "payment";
    }

    @GetMapping("/orderId")
    public MyOrder getOrderId(){

        JSONObject ob =new JSONObject();
        //ob.put("orderId",  myOrderService.findByMyOrderId(23));
        return myOrderService.findByMyOrderId(27);
    }





}
