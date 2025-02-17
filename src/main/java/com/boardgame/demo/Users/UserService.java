package com.boardgame.demo.Users;

import java.util.Optional;
import java.util.stream.Stream;

import jakarta.validation.constraints.NotNull;

public interface UserService {
    User create(UserCreationParams params);
    User get(String id);
    User getByEmail(String email);
    void delete(String id);
    User update(String id, UserCreationParams params);
    @NotNull Stream<User> findAll();
    Optional<User> findById(@NotNull String userId);
    @NotNull User upsert(@NotNull User user);
}
