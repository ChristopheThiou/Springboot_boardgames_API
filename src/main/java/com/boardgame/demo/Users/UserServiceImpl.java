package com.boardgame.demo.Users;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto create(UserCreationParams params) {
        return new UserDto(params.getEmail(), params.getPassword());
    }

    @Override
    public UserDto get(String id) {
        return new UserDto(id, " ");
    }

    @Override
    public UserDto getByEmail(String email) {
        return new UserDto("", email);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public UserDto update(String id, UserCreationParams params) {
        return new UserDto(id, params.getEmail());
    }
}
