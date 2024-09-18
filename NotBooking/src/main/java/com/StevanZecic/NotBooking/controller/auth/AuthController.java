package com.StevanZecic.NotBooking.controller.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StevanZecic.NotBooking.dto.AuthenticationRequest;
import com.StevanZecic.NotBooking.dto.AuthenticationResponse;
import com.StevanZecic.NotBooking.dto.SignupRequest;
import com.StevanZecic.NotBooking.dto.UserDTO;
import com.StevanZecic.NotBooking.entity.User;
import com.StevanZecic.NotBooking.repository.UserRepository;
import com.StevanZecic.NotBooking.services.auth.AuthService;
import com.StevanZecic.NotBooking.services.jwt.UserService;
import com.StevanZecic.NotBooking.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

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

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optUser = userRepository.findByUsername(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optUser.isPresent()) {
            authenticationResponse.setToken(jwt);
            authenticationResponse.setUserId(optUser.get().getId());
            authenticationResponse.setUserRole(optUser.get().getUserRole());
        }
        return authenticationResponse;
    }


}
