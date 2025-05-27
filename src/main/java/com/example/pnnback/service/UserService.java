package com.example.pnnback.service;

import com.example.pnnback.entity.User;
import com.example.pnnback.dto.UserJoinRequest;
import com.example.pnnback.dto.UserLoginRequest;
import com.example.pnnback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void join(UserJoinRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .name(req.getName())
                .build();

        userRepository.save(user);
    }

    public String login(UserLoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 틀림");
        }

        // 실제론 JWT 토큰을 리턴해야 함
        return "로그인 성공 (JWT 토큰 자리)";
    }
}
