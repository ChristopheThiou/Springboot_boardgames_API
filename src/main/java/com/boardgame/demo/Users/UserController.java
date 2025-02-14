package com.boardgame.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return userService.get(userId);
    }

    @GetMapping("/users")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.delete(userId);
    }

    @PutMapping("/users/{userId}")
    public UserDto updateUser(@PathVariable String userId, @RequestBody UserCreationParams params) {
        return userService.update(userId, params);
    }
}