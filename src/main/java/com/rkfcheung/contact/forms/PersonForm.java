package com.rkfcheung.contact.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {
    @NotNull
    @Size(min = 4, max = 32)
    private String username;

    @NotNull
    @Size(min = 4, max = 32)
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "username='" + username + "'" +
                ", password='***'" +
                '}';
    }
}