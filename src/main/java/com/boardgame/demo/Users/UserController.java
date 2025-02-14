package com.boardgame.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserCreationParams params) {
        return userService.create(params);
    }

    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return userService.get(uuid.toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }

    @GetMapping("/users")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @DeleteMapping("/users/delete/{userId}")
    public void deleteUser(@PathVariable String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            userService.delete(uuid.toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }

    @PutMapping("/users/patch/{userId}")
    public UserDto updateUser(@PathVariable String userId, @RequestBody UserCreationParams params) {
        try {
            UUID uuid = UUID.fromString(userId);
            return userService.update(uuid.toString(), params);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }
}