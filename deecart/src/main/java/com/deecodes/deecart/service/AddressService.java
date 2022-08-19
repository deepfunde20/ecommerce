package com.deecodes.deecart.service;

import com.deecodes.deecart.entity.Address;
import com.deecodes.deecart.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    public List<Address> getAddressList() {

      return  addressRepository.findAll();
    }

    public Address addAddress(Address address) {
      return  addressRepository.save(address);
    }

    public List<Address> getByUserId(long id){
      return  addressRepository.findAllByUserId(id);
    }

    public void deleteAddressById(Integer id) {
        addressRepository.deleteById(id);
    }

    public Address getByAddressId(Integer id) {
       return addressRepository.findById(id).get();
    }
}
