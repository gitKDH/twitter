package com.hyun.twitter.user.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
}
