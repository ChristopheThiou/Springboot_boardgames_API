package com.boardgame.demo.UsersDAO;

import java.util.*;
import java.util.stream.Stream;

import com.boardgame.demo.Users.User;
import com.boardgame.demo.Users.UserDao;

import jakarta.validation.constraints.NotNull;

public class InMemoryUserDao implements UserDao {

    private final Map<UUID, User> users = new HashMap<>();

    @Override
    public @NotNull Stream<User> findAll() {
        return users.values().stream();
    }

    @Override
    public @NotNull User upsert(@NotNull User user) {
        UUID userId = UUID.fromString(user.getId());
        users.put(userId, user);
        return user;
    }

    @Override
    public void deleteById(@NotNull String userId) {
        users.remove(UUID.fromString(userId));
    }

    @Override
    public Optional<User> findById(@NotNull String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return Optional.ofNullable(users.get(uuid));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}