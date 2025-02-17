package com.boardgame.demo.Users;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User create(UserCreationParams params) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, params.getEmail(), params.getPassword());
        return userDao.upsert(user);
    }

    @Override
    public User get(String id) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    @Override
    public void delete(String id) {
        userDao.deleteById(id);
    }

    @Override
    public User update(String id, UserCreationParams params) {
        User user = new User(id, params.getEmail(), params.getPassword());
        return userDao.upsert(user);
    }

    @Override
    public @NotNull Stream<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public @NotNull User upsert(@NotNull User user) {
        return userDao.upsert(user);
    }
}