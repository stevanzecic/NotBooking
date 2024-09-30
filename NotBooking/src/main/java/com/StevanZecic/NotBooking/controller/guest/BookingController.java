package com.StevanZecic.NotBooking.controller.guest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StevanZecic.NotBooking.dto.ReservationDTO;
import com.StevanZecic.NotBooking.services.guest.booking.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/guest")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> createBooking(@RequestBody ReservationDTO reservationDTO) {
        boolean isCreated = bookingService.createReservation(reservationDTO);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
