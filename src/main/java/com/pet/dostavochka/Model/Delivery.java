package com.pet.dostavochka.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Delivery")
public class Delivery extends BaseEntity {
    @Column(name = "street")
    private String street;

    @Column(name = "home")
    private String home;

    @Column(name = "floor")
    private String floor;

    @Column(name = "flat")
    private String flat;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    public Delivery(String street,
                    String home,
                    String floor,
                    String flat,
                    String firstName,
                    String lastName,
                    String phoneNumber,
                    String email) {
        this.street = street;
        this.home = home;
        this.floor = floor;
        this.flat = flat;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
