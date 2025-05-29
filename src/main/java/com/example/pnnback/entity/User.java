package com.example.pnnback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")  // 예약어 피하기 위해 테이블 이름을 users로 설정
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(name = "username", nullable = false)  // DB 컬럼 username과 매핑
    private String name;
}
