package com.StevanZecic.NotBooking.dto;

import java.time.LocalDate;

import com.StevanZecic.NotBooking.enums.ReservStatus;

import lombok.Data;

@Data
public class ReservationDTO {

    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Long price;
    private ReservStatus resStatus;

    private Long roomId;
    private String roomName;
    private String roomType;

    private Long userId;
    private String userName;

}
