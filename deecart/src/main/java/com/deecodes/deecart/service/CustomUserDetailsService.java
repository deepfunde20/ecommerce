package com.deecodes.deecart.service;

import com.deecodes.deecart.configuration.CustomUserDetails;
import com.deecodes.deecart.entity.MyUser;
import com.deecodes.deecart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser =userRepository.findByUsername(username);
        if(myUser ==null){
            throw  new UsernameNotFoundException("user not found");
        }
        return new CustomUserDetails(myUser);
    }

    public MyUser addNewUser(MyUser myUser){
      return  userRepository.save(myUser);
    }

    public MyUser findByUserName(String username) {
       return userRepository.findByUsername(username);
    }
}
