package com.boardgame.demo.Dao;

import com.boardgame.demo.Entity.UserEntity;
import com.boardgame.demo.Users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Primary
public class JpaUserDao implements UserDao {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public @NotNull Stream<User> findAll() {
        return userEntityRepository.findAll().stream().map(this::toDto);
    }

    @Override
    public Optional<User> findById(@NotNull @Size(min = 36, max = 36) String userId) {
        return userEntityRepository.findById(userId).map(this::toDto);
    }

    @Override
    public @NotNull User upsert(@Validated @NotNull User userDto) {
        UserEntity userEntity = toEntity(userDto);
        userEntity = userEntityRepository.save(userEntity);
        return toDto(userEntity);
    }

    @Override
    public void deleteById(@NotNull @Size(min = 36, max = 36) String userId) {
        userEntityRepository.deleteById(userId);
    }

    private User toDto(UserEntity userEntity) {
        return new User(userEntity.id, userEntity.email, userEntity.password);
    }

    private UserEntity toEntity(User userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = userDto.getId();
        userEntity.email = userDto.getEmail();
        userEntity.password = userDto.getPassword();
        return userEntity;
    }
}