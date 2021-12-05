package com.pet.dostavochka.DTO;

import com.pet.dostavochka.Model.Delivery;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
public class OrderDTO {
    @NotBlank(message = "Street cannot be empty")
    private String street;

    @NotBlank(message = "Home cannot be empty")
    private String home;

    @NotBlank(message = "Floor cannot be empty")
    private String floor;

    @NotBlank(message = "Flat cannot be empty")
    private String flat;

    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Phone Number cannot be empty")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String accountId;

    public Delivery ToDelivery() {
        return new Delivery(
                this.getStreet(),
                this.getHome(),
                this.getFloor(),
                this.getFlat(),
                this.getFirstName(),
                this.getLastName(),
                this.getPhoneNumber(),
                this.getEmail()
        );

    }
}
