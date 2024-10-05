package com.StevanZecic.NotBooking.controller.guest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StevanZecic.NotBooking.dto.ReservationDTO;
import com.StevanZecic.NotBooking.services.guest.booking.BookingService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;



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

    @GetMapping("/bookings/{userId}/{pgNum}")
    public ResponseEntity<?> getAllReservationByUserId(@PathVariable Long userId, @PathVariable int pgNum) {
        try {
            return ResponseEntity.ok(bookingService.getAllReservationByUserId(userId, pgNum));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops! Something went wrong");
        }
    }

    @RequestMapping(value="/bookings/{iD}/delete", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteReservation(@PathVariable Long iD) {
        try {
            bookingService.deleteReservation(iD);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops! Something went wrong");
        }
    }
}
