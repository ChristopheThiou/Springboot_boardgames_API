package com.boardgame.demo.dao;

import com.boardgame.demo.users.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;




@Repository
public class JdbcUserDao implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return new User(
                rs.getString("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("token")
            );
        }
    };

    @Override
    public @NotNull Stream<User> findAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM user_entity", userRowMapper);
        return users.stream();
    }

    @Override
    public Optional<User> findById(@NotNull @Size(min = 36, max = 36) String userId) {
        try {
            User user = jdbcTemplate.queryForObject(
                "SELECT * FROM user_entity WHERE id = ?",
                userRowMapper,
                userId
            );
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public @NotNull User upsert(@Valid @NotNull User user) {
        jdbcTemplate.update(
            "INSERT INTO user_entity (id, email, password) VALUES (?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE email = VALUES(email), password = VALUES(password)",
            user.getId(), user.getEmail(), user.getPassword()
        );
        return user;
    }

    @Override
    public void deleteById(@NotNull @Size(min = 36, max = 36) String userId) {
        jdbcTemplate.update("DELETE FROM user_entity WHERE id = ?", userId);
    }

    @Override
    public User getUserByEmailAndPassword(@NotNull String email, @NotNull String password) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM user_entity WHERE email = ? AND password = ?",
            userRowMapper,
            email, password
        );
    }
}