package com.StevanZecic.NotBooking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StevanZecic.NotBooking.entity.Room;

@Repository
public interface  RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findByIsAvailable(boolean avail, Pageable pageable);

}
