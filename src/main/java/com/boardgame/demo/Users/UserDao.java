package com.boardgame.demo.Users;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface UserDao {
    @NotNull Stream<User> findAll();
    
    Optional<User> findById(@NotNull @Size(min = 36, max = 36) String userId);
    
    @NotNull User upsert(@Validated @NotNull User user);
    
    void deleteById(@NotNull @Size(min = 36, max = 36) String userId);
}