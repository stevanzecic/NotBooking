package com.StevanZecic.NotBooking.services.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.StevanZecic.NotBooking.dto.SignupRequest;
import com.StevanZecic.NotBooking.dto.UserDTO;
import com.StevanZecic.NotBooking.entity.User;
import com.StevanZecic.NotBooking.enums.UserRole;
import com.StevanZecic.NotBooking.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Admin account created");
        } else {
            System.out.println("Admin account already exists");
        }
    }

    public UserDTO createUser(SignupRequest signupRequest) {

        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new EntityExistsException("User with this uswername already exists");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.GUEST);
        User newUser = userRepository.save(user);
        return newUser.getUserDTO();
    }

}
