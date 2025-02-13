package com.boardgame.demo.Users;

public interface UserService {
    UserDto create(UserCreationParams params);
    UserDto get(String id);
    UserDto getByEmail(String email);
    void delete(String id);
    UserDto update(String id, UserCreationParams params);
}
