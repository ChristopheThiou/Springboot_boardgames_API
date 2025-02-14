package com.boardgame.demo.UsersDAO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserEntity {
    @Id
    public String id;
    public @NotNull @Email String email;
    public @NotNull String password;
}