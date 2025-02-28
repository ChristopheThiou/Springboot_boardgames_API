package com.boardgame.demo.dao;

import com.boardgame.demo.users.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.validation.annotation.Validated;





public interface UserDao {
    @NotNull Stream<User> findAll();
    
    Optional<User> findById(@NotNull @Size(min = 36, max = 36) String userId);
    
    @NotNull User upsert(@Validated @NotNull User user);
    
    void deleteById(@NotNull @Size(min = 36, max = 36) String userId);
}