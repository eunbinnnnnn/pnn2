package com.example.pnnback.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SignUpRequest {
    private String username;
    private String password;
}
