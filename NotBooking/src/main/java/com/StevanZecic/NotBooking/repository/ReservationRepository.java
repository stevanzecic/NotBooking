package com.StevanZecic.NotBooking.repository;

import com.StevanZecic.NotBooking.entity.Reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findAllByUserId(Pageable pageable, Long userId);
    void deleteById(Long iD);

}
