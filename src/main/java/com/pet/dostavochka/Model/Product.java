package com.pet.dostavochka.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Product")
public class Product extends BaseEntity {
    @Column(name = "displayName")
    private String displayName;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private float price;

    public Product(String displayName, String description, String category, String image, float price) {
        this.displayName = displayName;
        this.description = description;
        this.category = category;
        this.image = image;
        this.price = price;
    }
}
