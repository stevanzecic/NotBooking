package com.StevanZecic.NotBooking.dto;

import com.StevanZecic.NotBooking.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String token;
    private Long userId;
    private UserRole userRole;

}
