package com.boardgame.demo.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserCreationParams {
    private final @NotNull @Email String email;
    private final @NotNull String password;

    public UserCreationParams(@NotNull @Email String email, @NotNull String password) {
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