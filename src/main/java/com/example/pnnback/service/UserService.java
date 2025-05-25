package com.example.pnnback.service;

import com.example.pnnback.domain.User;
import com.example.pnnback.dto.UserJoinRequest;
import com.example.pnnback.dto.UserLoginRequest;
import com.example.pnnback.jwt.JwtTokenProvider;
import com.example.pnnback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
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
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(user.getEmail());
    }
}
