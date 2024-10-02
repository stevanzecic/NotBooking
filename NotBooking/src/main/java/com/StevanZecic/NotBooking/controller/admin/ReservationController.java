package com.StevanZecic.NotBooking.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StevanZecic.NotBooking.services.admin.reservation.ReservationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations/{pgNum}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pgNum) {
        try {
            return ResponseEntity.ok(reservationService.getAllReservations(pgNum));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops! Something went wrong!");
        }
    }

    @GetMapping("/reservation/{resiD}/{resStatus}")
    public ResponseEntity<?> changeResStatus(@PathVariable Long resiD, @PathVariable String resStatus) {
        boolean success = reservationService.changeReservationStatus(resiD, resStatus);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops! Something went wrong!");
        }
    }


}
