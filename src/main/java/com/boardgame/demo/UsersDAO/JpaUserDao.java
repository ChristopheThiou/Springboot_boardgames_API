package com.boardgame.demo.UsersDAO;

import com.boardgame.demo.Users.UserDto;
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
    public @NotNull Stream<UserDto> findAll() {
        return userEntityRepository.findAll().stream().map(this::toDto);
    }

    @Override
    public Optional<UserDto> findById(@NotNull String userId) {
        
        return userEntityRepository.findById(userId).map(this::toDto);
    }

    @Override
    public @NotNull UserDto upsert(@NotNull UserDto userDto) {
        UserEntity userEntity = toEntity(userDto);
        userEntity = userEntityRepository.save(userEntity);
        return toDto(userEntity);
    }

    @Override
    public void deleteById(@NotNull UUID userId) {
        userEntityRepository.deleteById(userId.toString());
    }

    private UserDto toDto(UserEntity userEntity) {
        return new UserDto(userEntity.id, userEntity.email, userEntity.password);
    }

    private UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.email = userDto.getEmail();
        userEntity.password = userDto.getPassword();
        return userEntity;
    }
}