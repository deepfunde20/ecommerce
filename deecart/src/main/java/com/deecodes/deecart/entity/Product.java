package com.deecodes.deecart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    Category category;
    String price;

}
