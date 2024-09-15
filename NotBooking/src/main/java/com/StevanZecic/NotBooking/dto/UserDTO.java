package com.StevanZecic.NotBooking.dto;


import com.StevanZecic.NotBooking.enums.UserRole;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String name;

    private UserRole userRole;


    // public UserDTO(Long id, String username, String name, UserRole userRole) {
    //     this.id = id;
    //     this.username = username;
    //     this.name = name;
    //     this.userRole = userRole;
    // }

}
