package com.boardgame.demo.Users;

import java.util.stream.Stream;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface UserService {
    @NotNull User create(@Validated @NotNull UserCreationParams params);
    @NotNull User get(@NotNull @Size(min = 36, max = 36) String id);
    void delete(@NotNull @Size(min = 36, max = 36) String id);
    @NotNull User update(@NotNull @Size(min = 36, max = 36) String id, @Valid @NotNull UserCreationParams params);
    @NotNull Stream<User> findAll();
    @NotNull User upsert(@Valid @NotNull User user);
}