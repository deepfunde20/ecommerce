package com.deecodes.deecart.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class MyUser {
    @Id
    private Long id;
    private String username;
    private String password;
    private String role;

}
