package com.deecodes.deecart.repository;

import com.deecodes.deecart.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
  public  List<Address> findAllByUserId(long id);
}
