package com.StevanZecic.NotBooking.dto;


import lombok.Data;
import java.util.List;

@Data
public class RoomsResponseDTO {

    private List<RoomDTO> rooms;

    private Integer totPages;

    private Integer currPage;

}
