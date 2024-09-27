package com.StevanZecic.NotBooking.entity;

import com.StevanZecic.NotBooking.dto.RoomDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long price;

    private boolean isAvailable;

    public RoomDTO getRoomDTO() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(this.id);
        roomDTO.setName(this.name);
        roomDTO.setType(this.type);
        roomDTO.setPrice(this.price);
        roomDTO.setAvailable(this.isAvailable);
        return roomDTO;
    }

}
