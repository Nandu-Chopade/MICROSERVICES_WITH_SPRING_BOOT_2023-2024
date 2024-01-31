package com.user.entity;

import lombok.Data;

@Data
public class Product {

    private Long pid;
    private String productName;
    private  String price;
    private String productDescription;
}
