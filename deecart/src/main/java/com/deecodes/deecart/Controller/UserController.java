package com.deecodes.deecart.Controller;

import com.deecodes.deecart.entity.Address;
import com.deecodes.deecart.entity.MyUser;
import com.deecodes.deecart.entity.Product;
import com.deecodes.deecart.service.AddressService;
import com.deecodes.deecart.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    AddressService addressService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/address")
    public String getAddressList(Authentication authentication,Model model){
        long id = customUserDetailsService.findByUserName(authentication.getName()).getId();
        model.addAttribute("addressList",addressService.getByUserId(id));
        model.addAttribute("userId",id);
        return "/addressPage";
    }

    @GetMapping("/address/add")
    public String addAddress(Authentication authentication, Model model){
       long id = customUserDetailsService.findByUserName(authentication.getName()).getId();
        model.addAttribute("address", new Address());
        model.addAttribute("userId",id);
        System.out.println("This is id of user "+ id);
        return "addAddressPage";
    }

    @PostMapping("/address/add")
    public String addAddress(Authentication authentication, @ModelAttribute("address") Address address){
        long id = customUserDetailsService.findByUserName(authentication.getName()).getId();
        System.out.println("This is id of user "+ id);
        Address tempAddress = new Address();
        tempAddress.setId(address.getId());
        tempAddress.setStreetName(address.getStreetName());
        tempAddress.setUserId(id);
        tempAddress.setCity(address.getCity());
        tempAddress.setPostalCode(address.getPostalCode());
         addressService.addAddress(tempAddress);
        return "redirect:/user/address/";
    }
    @GetMapping("/address/delete/{id}")
    private String removeProduct(@PathVariable Integer id){
       Address add =  addressService.getByAddressId(id);
        addressService.deleteAddressById(id);
        return "redirect:/user/address/";
    }

    @GetMapping("/address/update/{id}")
    public String updateAddress(@PathVariable Integer id, Model model){

      Address address=  addressService.getByAddressId(id);
        Address tempAddress = new Address();
        tempAddress.setId(address.getId());
        tempAddress.setStreetName(address.getStreetName());
        tempAddress.setUserId(address.getUserId());
        tempAddress.setCity(address.getCity());
        tempAddress.setPostalCode(address.getPostalCode());
        model.addAttribute("address", tempAddress);
        model.addAttribute("userId",address.getUserId());
        return "addAddressPage";
    }
    @PostMapping("/signup")
    public String addUser(@ModelAttribute("myUser")MyUser myUser) throws Exception {
       MyUser tempUser = new MyUser();
       tempUser.setId(myUser.getId());
       tempUser.setUsername(myUser.getUsername());
       tempUser.setEmail(myUser.getEmail());
      MyUser existingUser =  customUserDetailsService.findByUserName( myUser.getUsername());
      if(existingUser !=null){
          throw new Exception("User already exist");
      }
       tempUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
       tempUser.setRole("USER");
        customUserDetailsService.addNewUser(tempUser);
        return "redirect:/login";
    }
    @GetMapping("/signup")
    public String getAddUser(Model model){
        model.addAttribute("myUser", new MyUser());
        return "newsignup";
    }

    @GetMapping("/login")
    public String login(){
        return "newlogin";
    }

}
