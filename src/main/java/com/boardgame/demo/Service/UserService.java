package com.boardgame.demo.service;

import com.boardgame.demo.dto.UserCreationParams;
import com.boardgame.demo.dto.UserDto;
import com.boardgame.demo.users.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.stream.Stream;
import org.springframework.validation.annotation.Validated;


public interface UserService {
    @NotNull UserDto create(@Validated @NotNull UserCreationParams params);
    @NotNull UserDto get(@NotNull @Size(min = 36, max = 36) String id);
    void delete(@NotNull @Size(min = 36, max = 36) String id);
    @NotNull UserDto update(@NotNull @Size(min = 36, max = 36) String id, @Validated@NotNull UserCreationParams params);
    @NotNull Stream<UserDto> findAll();
    @NotNull UserDto upsert(@Validated@NotNull User user);
    UserDto getUserByEmailAndPassword(@NotNull String email, @NotNull String password);
}