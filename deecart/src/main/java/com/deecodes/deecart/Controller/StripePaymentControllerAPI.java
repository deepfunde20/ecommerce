package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.MyUser;
import com.deecodes.deecart.service.CustomUserDetailsService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/api")
public class StripePaymentControllerAPI {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Value("${stripe.apikey}")
    String stripeKey;

    @RequestMapping("/createCustomer")
    public MyUser index(Authentication authentication) throws StripeException {
      MyUser currentUser=  customUserDetailsService.findByUserName(authentication.getName());
      if(currentUser.getCustomerId()==null){
          Stripe.apiKey = stripeKey;
          Map<String, Object> params = new HashMap<>();
          params.put("name",currentUser.getUsername());
          params.put("email",currentUser.getEmail());
          Customer customer = Customer.create(params);
          currentUser.setCustomerId(customer.getId());
          customUserDetailsService.addNewUser(currentUser);
          return currentUser;
      }
      else{
          System.out.println("====================Already registered with Stripe payment=====================");
          return currentUser;
      }

    }
}
