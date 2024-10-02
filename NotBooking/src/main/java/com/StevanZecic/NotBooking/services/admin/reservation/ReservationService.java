package com.StevanZecic.NotBooking.services.admin.reservation;

import com.StevanZecic.NotBooking.dto.ReservationResponseDTO;

public interface ReservationService {

    ReservationResponseDTO getAllReservations(int pgNum);
    boolean changeReservationStatus(Long iD, String resStatus);

}
