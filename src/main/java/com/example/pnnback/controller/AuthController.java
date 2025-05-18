package com.example.pnnback.controller;

import com.example.pnnback.dto.*;
import com.example.pnnback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignUpRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        boolean result = authService.login(request);
        return result ? "로그인 성공!" : "로그인 실패!";
    }

    @PostMapping("/logout")
    public String logout() {
        // 세션/토큰이 없으므로 단순 로그아웃 메시지만 반환
        return "로그아웃 완료!";
    }
}
