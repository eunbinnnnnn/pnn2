package com.example.pnnback.Entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username; // 사용자 ID
    @Column(name = "password",nullable = false)
    private String password; // 암호화된 비밀번호
}
