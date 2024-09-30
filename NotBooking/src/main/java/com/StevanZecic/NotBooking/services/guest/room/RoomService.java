package com.StevanZecic.NotBooking.services.guest.room;

import com.StevanZecic.NotBooking.dto.RoomsResponseDTO;

public interface RoomService {

    RoomsResponseDTO getAvailableRooms(int pgNum);

}
