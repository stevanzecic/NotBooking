 package com.StevanZecic.NotBooking.services.guest.booking;

import java.lang.StackWalker.Option;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.StevanZecic.NotBooking.dto.ReservationDTO;
import com.StevanZecic.NotBooking.dto.ReservationResponseDTO;
import com.StevanZecic.NotBooking.entity.Reservation;
import com.StevanZecic.NotBooking.entity.Room;
import com.StevanZecic.NotBooking.entity.User;
import com.StevanZecic.NotBooking.enums.ReservStatus;
import com.StevanZecic.NotBooking.repository.ReservationRepository;
import com.StevanZecic.NotBooking.repository.RoomRepository;
import com.StevanZecic.NotBooking.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public static final int RES_PER_PAGE = 6;

    public boolean createReservation(ReservationDTO reservationDTO) {
        Optional<User> user = userRepository.findById(reservationDTO.getUserId());
        Optional<Room> room = roomRepository.findById(reservationDTO.getRoomId());
        if (user.isPresent() && room.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setUser(user.get());
            reservation.setRoom(room.get());
            reservation.setCheckIn(reservationDTO.getCheckIn());
            reservation.setCheckOut(reservationDTO.getCheckOut());
            reservation.setResStatus(ReservStatus.PENDING);

            Long numOfDays = ChronoUnit.DAYS.between(reservationDTO.getCheckIn(), reservationDTO.getCheckOut());
            reservation.setPrice(room.get().getPrice() * numOfDays);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public ReservationResponseDTO getAllReservationByUserId(Long userId, int pgNum) {
        Pageable pageable = PageRequest.of(pgNum, RES_PER_PAGE);
        Page<Reservation> reservationPg = reservationRepository.findAllByUserId(pageable, userId);
        ReservationResponseDTO reservationRespDTO = new ReservationResponseDTO();
        reservationRespDTO.setResDTOList(reservationPg.stream().map(Reservation::getReservationDTO).collect(Collectors.toList()));
        reservationRespDTO.setPgNum(reservationPg.getPageable().getPageNumber());
        reservationRespDTO.setTotPgs(reservationPg.getTotalPages());
        return reservationRespDTO;
    }

    public void deleteReservation(Long iD) {
        Optional<Reservation> res = reservationRepository.findById(iD);
        if (res.isPresent()) {
            reservationRepository.deleteById(iD);
        } else {
            throw new EntityNotFoundException("Reservation not found");
        }
    }
}
