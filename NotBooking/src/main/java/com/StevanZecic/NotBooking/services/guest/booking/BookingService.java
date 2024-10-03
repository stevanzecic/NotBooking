package com.StevanZecic.NotBooking.services.guest.booking;

import com.StevanZecic.NotBooking.dto.ReservationDTO;
import com.StevanZecic.NotBooking.dto.ReservationResponseDTO;

public interface BookingService {

    boolean createReservation(ReservationDTO reservationDTO);
    ReservationResponseDTO getAllReservationByUserId(Long userId, int pgNum);

}
