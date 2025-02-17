package com.boardgame.demo.Users;

import java.util.Optional;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public User get(String id) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    @Override
    public User getByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByEmail'");
    }

    @Override
    public void delete(String id) {
        UUID userId = UUID.fromString(id);
        userDao.deleteById(userId);
    }

    @Override
    public User update(String id, UserCreationParams params) {
        String userId = UUID.fromString(id).toString();
        User userDto = new User(userId, params.getEmail(), params.getPassword());
        return userDao.upsert(userDto);
    }

    @Override
    public @NotNull Stream<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(@NotNull String userId) {
        return userDao.findById(userId);
    }

    @Override
    public @NotNull User upsert(@NotNull User user) {
        return userDao.upsert(user);
    }
}