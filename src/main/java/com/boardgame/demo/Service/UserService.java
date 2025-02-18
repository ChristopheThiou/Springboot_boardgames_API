package com.boardgame.demo.Service;

import java.util.stream.Stream;

import org.springframework.validation.annotation.Validated;

import com.boardgame.demo.Dto.UserCreationParams;
import com.boardgame.demo.Users.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface UserService {
    @NotNull User create(@Validated @NotNull UserCreationParams params);
    @NotNull User get(@NotNull @Size(min = 36, max = 36) String id);
    void delete(@NotNull @Size(min = 36, max = 36) String id);
    @NotNull User update(@NotNull @Size(min = 36, max = 36) String id, @Validated@NotNull UserCreationParams params);
    @NotNull Stream<User> findAll();
    @NotNull User upsert(@Validated@NotNull User user);
}