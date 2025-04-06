package com.boardgame.demo.controller;

import com.boardgame.demo.dto.TokenDto;
import com.boardgame.demo.dto.UserCreationParams;
import com.boardgame.demo.dto.UserCredentialsDto;
import com.boardgame.demo.service.UserService;
import com.boardgame.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public TokenDto signup(@RequestBody UserCreationParams params) {
        userService.create(params);
        return getAuthenticate(new UserCredentialsDto(params.getEmail(), params.getPassword()));
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserCredentialsDto userCredentialsDto) {
        return getAuthenticate(userCredentialsDto);
    }

    private TokenDto getAuthenticate(UserCredentialsDto userCredentialsDto) {
        final Authentication authenticate = authenticationManager
            .authenticate(
                new UsernamePasswordAuthenticationToken(
                    userCredentialsDto.email(), userCredentialsDto.password()
                )
            );
        String token = jwtUtil.generateToken(userCredentialsDto.email());
        return new TokenDto(token, userCredentialsDto.email());
    }
}