package com.deecodes.deecart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToMany(fetch= FetchType.LAZY)
    private List<Product> product;
    private int quantity;
    private long userId;
}
