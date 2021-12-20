package com.pet.dostavochka.DTO;

import com.pet.dostavochka.Model.Product;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ProductDTO {
    @NotBlank(message = "DisplayName cannot be empty")
    private String displayName;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    private String description;

    private float price;

    @NotBlank(message = "Image Name cannot be empty")
    private String image;

    public Product toProduct() {
        return new Product(
                this.getDisplayName(),
                this.getDescription(),
                this.getCategory(),
                this.getImage(),
                this.getPrice()
        );
    }
}
