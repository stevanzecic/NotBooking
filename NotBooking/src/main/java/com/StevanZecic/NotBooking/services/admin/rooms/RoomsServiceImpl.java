package com.StevanZecic.NotBooking.services.admin.rooms;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.StevanZecic.NotBooking.dto.RoomDTO;
import com.StevanZecic.NotBooking.dto.RoomsResponseDTO;
import com.StevanZecic.NotBooking.entity.Room;
import com.StevanZecic.NotBooking.repository.RoomRepository;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {

    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDTO roomDTO) {
        try {
            Room room = new Room();
            room.setName(roomDTO.getName());
            room.setType(roomDTO.getType());
            room.setPrice(roomDTO.getPrice());
            room.setAvailable(true);
            roomRepository.save(room);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public RoomsResponseDTO getAllRooms(int pgNum) {
        Pageable pageble = PageRequest.of(pgNum, 3);
        Page<Room> page = roomRepository.findAll(pageble);
        RoomsResponseDTO roomsResponseDTO = new RoomsResponseDTO();
        roomsResponseDTO.setCurrPage(page.getPageable().getPageNumber());
        roomsResponseDTO.setTotPages(page.getTotalPages());
        roomsResponseDTO.setRooms(page.stream().map(Room::getRoomDTO).collect(Collectors.toList()));
        return roomsResponseDTO;
    }
}
