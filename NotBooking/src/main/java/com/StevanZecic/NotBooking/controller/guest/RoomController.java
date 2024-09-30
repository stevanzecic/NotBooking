package com.StevanZecic.NotBooking.controller.guest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StevanZecic.NotBooking.services.guest.room.RoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class RoomController {

    private final RoomService roomsService;

    @GetMapping("/rooms/{pgNum}")
    public ResponseEntity<?> getAvailableRooms(@PathVariable int pgNum) {
        return ResponseEntity.ok(roomsService.getAvailableRooms(pgNum));
    }


}
