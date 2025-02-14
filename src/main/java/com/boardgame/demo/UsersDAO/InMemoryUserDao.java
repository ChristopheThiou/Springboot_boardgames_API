package com.boardgame.demo.UsersDAO;

import java.util.*;
import java.util.stream.Stream;

import com.boardgame.demo.Users.UserDto;

import jakarta.validation.constraints.NotNull;

public class InMemoryUserDao implements UserDao {

    private final Map<UUID, UserDto> users = new HashMap<>();

    @Override
    public @NotNull Stream<UserDto> findAll() {
        return users.values().stream();
    }

    @Override
    public @NotNull UserDto upsert(@NotNull UserDto user) {
        UUID userId = UUID.fromString(user.getId());
        users.put(userId, user);
        return user;
    }

    @Override
    public void deleteById(@NotNull UUID userId) {
        users.remove(userId);
    }

    @Override
    public Optional<UserDto> findById(@NotNull String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}