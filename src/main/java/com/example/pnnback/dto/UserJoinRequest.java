package com.example.pnnback.dto;

import lombok.Getter;

@Getter
public class UserJoinRequest {
    private String email;
    private String password;
    private String name;
}
