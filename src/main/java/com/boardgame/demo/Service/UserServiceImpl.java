package com.boardgame.demo.service;

import com.boardgame.demo.dao.UserDao;
import com.boardgame.demo.dto.UserCreationParams;
import com.boardgame.demo.dto.UserDto;
import com.boardgame.demo.users.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDto create(@Validated @NotNull UserCreationParams params) {
        String userId = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(params.getPassword());
     
        User user = new User(userId, params.getEmail(), encodedPassword);
        return toDto(userDao.upsert(user));
    }

    @Override
    public UserDto get(@NotNull @Size(min = 36, max = 36) String id) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return toDto(user);
    }

    @Override
    public void delete(@NotNull @Size(min = 36, max = 36) String id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDto update(@NotNull @Size(min = 36, max = 36) String id, @Validated @NotNull UserCreationParams params) {
        String encodedPassword = passwordEncoder.encode(params.getPassword());
        User user = new User(id, params.getEmail(), encodedPassword);
        return toDto(userDao.upsert(user));
    }

    @Override
    public @NotNull Stream<UserDto> findAll() {
        return userDao.findAll().map(this::toDto);
    }

    @Override
    public @NotNull UserDto upsert(@Validated @NotNull User user) {
        return toDto(userDao.upsert(user));
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail());
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        User user = userDao.getUserByEmailAndPassword(email, password);
        return toDto(user);
    }
}