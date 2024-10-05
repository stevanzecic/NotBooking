package com.StevanZecic.NotBooking.services.admin.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.StevanZecic.NotBooking.dto.ReservationResponseDTO;
import com.StevanZecic.NotBooking.entity.Reservation;
import com.StevanZecic.NotBooking.entity.Room;
import com.StevanZecic.NotBooking.enums.ReservStatus;
import com.StevanZecic.NotBooking.repository.ReservationRepository;
import com.StevanZecic.NotBooking.repository.RoomRepository;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    public static final int RES_PER_PAGE = 6;
    private final RoomRepository roomRepository;

    public ReservationResponseDTO getAllReservations(int pgNum) {
        Pageable pageable = PageRequest.of(pgNum, RES_PER_PAGE);
        Page<Reservation> reservationPg = reservationRepository.findAll(pageable);
        ReservationResponseDTO reservationRespDTO = new ReservationResponseDTO();
        reservationRespDTO.setResDTOList(reservationPg.stream().map(Reservation::getReservationDTO).collect(Collectors.toList()));
        reservationRespDTO.setPgNum(reservationPg.getPageable().getPageNumber());
        reservationRespDTO.setTotPgs(reservationPg.getTotalPages());
        return reservationRespDTO;
    }

    public boolean changeReservationStatus(Long iD, String resStatus) {
        Optional<Reservation> optReservation = reservationRepository.findById(iD);
        if (optReservation.isPresent()) {
            Reservation res = optReservation.get();

            if (Objects.equals(resStatus, "Confirm")) {
                res.setResStatus(ReservStatus.CONFIRMED);
            } else {
                res.setResStatus(ReservStatus.CANCELLED);
            }
            reservationRepository.save(res);
            Room room = res.getRoom();
            if (res.getResStatus() == ReservStatus.CONFIRMED) {
                room.setAvailable(false);
            } else {
                room.setAvailable(true);
            }
            roomRepository.save(room);
            return true;
        }
        return false;
    }

}
