package com.example.pnnback.controller;

import com.example.pnnback.dto.UserJoinRequest;
import com.example.pnnback.dto.UserLoginRequest;
import com.example.pnnback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserJoinRequest request) {
        userService.join(request);
        return "회원가입 완료";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }
}
