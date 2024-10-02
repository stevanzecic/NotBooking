package com.StevanZecic.NotBooking.dto;

import lombok.Data;
import java.util.List;

@Data
public class ReservationResponseDTO {

    private Integer totPgs;
    private Integer pgNum;
    private List<ReservationDTO> resDTOList;

}
