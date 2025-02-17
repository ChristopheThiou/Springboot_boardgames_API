package com.boardgame.demo.Users;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User create(@Validated @NotNull UserCreationParams params) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, params.getEmail(), params.getPassword());
        return userDao.upsert(user);
    }

    @Override
    public User get(@NotNull @Size(min = 36, max = 36) String id) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    @Override
    public void delete(@NotNull @Size(min = 36, max = 36) String id) {
        userDao.deleteById(id);
    }

    @Override
    public User update(@NotNull @Size(min = 36, max = 36) String id, @Validated@NotNull UserCreationParams params) {
        User user = new User(id, params.getEmail(), params.getPassword());
        return userDao.upsert(user);
    }

    @Override
    public @NotNull Stream<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public @NotNull User upsert(@Validated @NotNull User user) {
        return userDao.upsert(user);
    }
}