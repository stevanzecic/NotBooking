package com.StevanZecic.NotBooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StevanZecic.NotBooking.entity.User;
import com.StevanZecic.NotBooking.enums.UserRole;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Optional<User> findFirstByEmail(String username);
    Optional<User> findByUsername(String username);

    Optional<User> findByUserRole(UserRole userRole);

}
