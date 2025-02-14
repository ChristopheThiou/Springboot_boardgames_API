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
    public UserDto create(UserCreationParams params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public UserDto get(String id) {
        UserDto user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    @Override
    public UserDto getByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByEmail'");
    }

    @Override
    public void delete(String id) {
        UUID userId = UUID.fromString(id);
        userDao.deleteById(userId);
    }

    @Override
    public UserDto update(String id, UserCreationParams params) {
        String userId = UUID.fromString(id).toString();
        UserDto userDto = new UserDto(userId, params.getEmail(), params.getPassword());
        return userDao.upsert(userDto);
    }

    @Override
    public @NotNull Stream<UserDto> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<UserDto> findById(@NotNull String userId) {
        return userDao.findById(userId);
    }

    @Override
    public @NotNull UserDto upsert(@NotNull UserDto user) {
        return userDao.upsert(user);
    }
}