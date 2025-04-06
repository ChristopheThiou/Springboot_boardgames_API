package com.boardgame.demo.controller;

import com.boardgame.demo.dto.UserCreationParams;
import com.boardgame.demo.dto.UserDto;
import com.boardgame.demo.service.UserService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Validated UserCreationParams params) {
        return userService.create(params);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable @NotNull @Size(min = 36, max = 36) String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return userService.get(uuid.toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable @NotNull @Size(min = 36, max = 36) String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            userService.delete(uuid.toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable @NotNull @Size(min = 36, max = 36) String userId, @RequestBody @Validated UserCreationParams params) {
        try {
            UUID uuid = UUID.fromString(userId);
            return userService.update(uuid.toString(), params);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }
}