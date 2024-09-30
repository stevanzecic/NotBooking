 package com.StevanZecic.NotBooking.services.guest.booking;

import java.lang.StackWalker.Option;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.StevanZecic.NotBooking.dto.ReservationDTO;
import com.StevanZecic.NotBooking.entity.Reservation;
import com.StevanZecic.NotBooking.entity.Room;
import com.StevanZecic.NotBooking.entity.User;
import com.StevanZecic.NotBooking.enums.ReservStatus;
import com.StevanZecic.NotBooking.repository.ReservationRepository;
import com.StevanZecic.NotBooking.repository.RoomRepository;
import com.StevanZecic.NotBooking.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

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

}
