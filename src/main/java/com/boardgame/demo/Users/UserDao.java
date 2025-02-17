package com.boardgame.demo.Users;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import jakarta.validation.constraints.NotNull;

public interface UserDao {
    @NotNull Stream<User> findAll();
    Optional<User> findById(@NotNull String userId);
    @NotNull User upsert(@NotNull User user);
    void deleteById(@NotNull UUID userId);
}