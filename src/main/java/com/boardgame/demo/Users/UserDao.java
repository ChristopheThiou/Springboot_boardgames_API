package com.boardgame.demo.Users;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import jakarta.validation.constraints.NotNull;

public interface UserDao {
    @NotNull Stream<UserDto> findAll();
    Optional<UserDto> findById(@NotNull String userId);
    @NotNull UserDto upsert(@NotNull UserDto user);
    void deleteById(@NotNull UUID userId);
}