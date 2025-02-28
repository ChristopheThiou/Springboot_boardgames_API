package com.boardgame.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class UserEntity {
    @Id
    @NotNull
    @Size(min = 36, max = 36)
    public String id;

    @NotNull
    @Email
    public String email;

    @NotNull
    @Size(min = 8, max = 100)
    public String password;
}