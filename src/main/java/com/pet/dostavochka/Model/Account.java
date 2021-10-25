package com.pet.dostavochka.Model;

import lombok.Data;

@Data
public class Account {
    private int id;
    private String login;
    private String password;

    public Account(String login, String password) {
        this.login = login;
        // TODO: hash passwords
        this.password = password;
    }
}
