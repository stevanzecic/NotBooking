package com.StevanZecic.NotBooking.services.guest.booking;

import com.StevanZecic.NotBooking.dto.ReservationDTO;

public interface BookingService {

    boolean createReservation(ReservationDTO reservationDTO);

}
