package com.scaler.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String title;
    private double price;
    private String description;
    private String imageURL;
    private Category category;
}
