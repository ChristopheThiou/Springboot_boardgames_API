package com.boardgame.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
    private @NotNull @Size(min = 36, max = 36) String id;
    private @NotNull @Email String email;

    public UserDto(@NotNull @Size(min = 36, max = 36) String id, @NotNull @Email String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}