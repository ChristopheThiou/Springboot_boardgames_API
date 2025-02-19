package com.boardgame.demo.Controller;

import com.boardgame.demo.Dto.UserCreationParams;
import com.boardgame.demo.Dto.UserDto;
import com.boardgame.demo.Service.UserService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDto createUser(@RequestBody @Validated UserCreationParams params) {
        return userService.create(params);
    }

    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable @NotNull @Size(min = 36, max = 36) String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return userService.get(uuid.toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }

    @DeleteMapping("/users/delete/{userId}")
    public void deleteUser(@PathVariable @NotNull @Size(min = 36, max = 36) String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            userService.delete(uuid.toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }

    @PutMapping("/users/patch/{userId}")
    public UserDto updateUser(@PathVariable @NotNull @Size(min = 36, max = 36) String userId, @RequestBody @Validated UserCreationParams params) {
        try {
            UUID uuid = UUID.fromString(userId);
            return userService.update(uuid.toString(), params);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID string: " + userId);
        }
    }
}