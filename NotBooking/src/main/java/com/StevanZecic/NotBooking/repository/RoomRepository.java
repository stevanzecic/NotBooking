package com.StevanZecic.NotBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StevanZecic.NotBooking.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
