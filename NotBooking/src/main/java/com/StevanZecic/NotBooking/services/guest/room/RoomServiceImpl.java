package com.StevanZecic.NotBooking.services.guest.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.StevanZecic.NotBooking.dto.RoomsResponseDTO;
import com.StevanZecic.NotBooking.entity.Room;
import com.StevanZecic.NotBooking.repository.RoomRepository;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomsResponseDTO getAvailableRooms(int pgNum) {
        Pageable pageble = PageRequest.of(pgNum, 6);
        Page<Room> page = roomRepository.findByIsAvailable(true, pageble);
        RoomsResponseDTO roomsResponseDTO = new RoomsResponseDTO();
        roomsResponseDTO.setCurrPage(page.getPageable().getPageNumber());
        roomsResponseDTO.setTotPages(page.getTotalPages());
        roomsResponseDTO.setRooms(page.stream().map(Room::getRoomDTO).collect(Collectors.toList()));
        return roomsResponseDTO;
    }

}
