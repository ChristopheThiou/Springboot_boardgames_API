package com.boardgame.demo.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserCreationParams {
    private final @NotNull @Email String email;

    public UserCreationParams(@NotNull @Email String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}