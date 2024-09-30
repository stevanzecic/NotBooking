package com.StevanZecic.NotBooking.services.admin.rooms;

import com.StevanZecic.NotBooking.dto.RoomDTO;
import com.StevanZecic.NotBooking.dto.RoomsResponseDTO;

public interface RoomsService {

    public boolean postRoom(RoomDTO roomDTO);

    RoomsResponseDTO getAllRooms(int pgNum);

    RoomDTO getRoomById(Long id);

    boolean updateRoom(Long iD, RoomDTO roomDTO);

    void deleteRoom(Long iD);

}
