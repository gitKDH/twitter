package com.hyun.twitter.user.dto;


public class PasswordChangeRequestDto {
    private String currentPassword;
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }
    public String getCurrentPassword() {
        return currentPassword;
    }

}
