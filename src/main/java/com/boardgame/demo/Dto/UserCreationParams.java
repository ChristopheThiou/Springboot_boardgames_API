package com.boardgame.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreationParams {
    private final @NotNull @Email String email;
    private final @NotNull @Size(min = 8, max = 100) String password;

    public UserCreationParams(@NotNull @Email String email, @NotNull @Size(min = 8, max = 100) String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}