package com.boardgame.demo.dao;

import com.boardgame.demo.entity.UserEntity;
import com.boardgame.demo.users.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

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
        return new User(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRoles());
    }

    private UserEntity toEntity(User userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

    @Override
    public User getUserByEmailAndPassword(@NotNull String email, @NotNull String password) {
        return userEntityRepository.findByEmailAndPassword(email, password).map(this::toDto).orElse(null);
    }
}
