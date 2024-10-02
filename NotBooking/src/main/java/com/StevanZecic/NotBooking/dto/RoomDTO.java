package com.StevanZecic.NotBooking.dto;

import lombok.Data;

@Data
public class RoomDTO {

    private Long iD;
    private String name;
    private String type;
    private Long price;
    private boolean isAvailable;

}
