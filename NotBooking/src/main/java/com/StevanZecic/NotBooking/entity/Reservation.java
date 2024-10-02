package com.StevanZecic.NotBooking.entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.StevanZecic.NotBooking.dto.ReservationDTO;
import com.StevanZecic.NotBooking.enums.ReservStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Long price;
    private ReservStatus resStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public ReservationDTO getReservationDTO() {
        ReservationDTO resDTO = new ReservationDTO();

        resDTO.setID(iD);
        resDTO.setPrice(price);
        resDTO.setCheckIn(checkIn);
        resDTO.setCheckOut(checkOut);
        resDTO.setResStatus(resStatus);

        resDTO.setUserId(user.getId());
        resDTO.setUserName(user.getUsername());

        resDTO.setRoomId(room.getID());
        resDTO.setRoomName(room.getName());
        resDTO.setRoomType(room.getType());

        return resDTO;
    }

}
