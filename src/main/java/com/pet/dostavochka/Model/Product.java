package com.pet.dostavochka.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Product")
public class Product extends BaseEntity {
    @Column(name = "displayName")
    private String displayName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;
}
