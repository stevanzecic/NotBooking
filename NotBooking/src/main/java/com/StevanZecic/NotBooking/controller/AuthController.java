package com.StevanZecic.NotBooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StevanZecic.NotBooking.dto.SignupRequest;
import com.StevanZecic.NotBooking.dto.UserDTO;
import com.StevanZecic.NotBooking.services.auth.AuthService;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        try {
            UserDTO newUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>("User with this username already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating user", HttpStatus.BAD_REQUEST);
        }
    }


}
