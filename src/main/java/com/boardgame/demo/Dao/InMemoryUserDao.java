package com.boardgame.demo.dao;

import com.boardgame.demo.users.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.*;
import java.util.stream.Stream;
import org.springframework.validation.annotation.Validated;





public class InMemoryUserDao implements UserDao {

    private final Map<UUID, User> users = new HashMap<>();

    @Override
    public @NotNull Stream<User> findAll() {
        return users.values().stream();
    }

    @Override
    public @NotNull User upsert(@Validated @NotNull User user) {
        UUID userId = UUID.fromString(user.getId());
        users.put(userId, user);
        return user;
    }

    @Override
    public void deleteById(@NotNull @Size(min = 36, max = 36) String userId) {
        users.remove(UUID.fromString(userId));
    }

    @Override
    public Optional<User> findById(@NotNull @Size(min = 36, max = 36) String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return Optional.ofNullable(users.get(uuid));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}