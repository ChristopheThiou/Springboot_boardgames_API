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
    public void deleteById(@NotNull UUID userId) {
        users.remove(userId);
    }

    @Override
    public Optional<User> findById(@NotNull String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}