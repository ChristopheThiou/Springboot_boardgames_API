package com.boardgame.demo.Users;

import java.util.Optional;
import java.util.stream.Stream;

import jakarta.validation.constraints.NotNull;

public interface UserService {
    UserDto create(UserCreationParams params);
    UserDto get(String id);
    UserDto getByEmail(String email);
    void delete(String id);
    UserDto update(String id, UserCreationParams params);
    @NotNull Stream<UserDto> findAll();
    Optional<UserDto> findById(@NotNull String userId);
    @NotNull UserDto upsert(@NotNull UserDto user);
}
