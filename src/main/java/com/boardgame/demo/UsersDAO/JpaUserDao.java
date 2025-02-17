package com.boardgame.demo.UsersDAO;

import com.boardgame.demo.Users.User;
import com.boardgame.demo.Users.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class JpaUserDao implements UserDao {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public @NotNull Stream<User> findAll() {
        return userEntityRepository.findAll().stream().map(this::toDto);
    }

    @Override
    public Optional<User> findById(@NotNull String userId) {
        
        return userEntityRepository.findById(userId).map(this::toDto);
    }

    @Override
    public @NotNull User upsert(@NotNull User userDto) {
        UserEntity userEntity = toEntity(userDto);
        userEntity = userEntityRepository.save(userEntity);
        return toDto(userEntity);
    }

    @Override
    public void deleteById(@NotNull UUID userId) {
        userEntityRepository.deleteById(userId.toString());
    }

    private User toDto(UserEntity userEntity) {
        return new User(userEntity.id, userEntity.email, userEntity.password);
    }

    private UserEntity toEntity(User userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.email = userDto.getEmail();
        return userEntity;
    }
}