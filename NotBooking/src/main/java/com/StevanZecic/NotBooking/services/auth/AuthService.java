package com.StevanZecic.NotBooking.services.auth;

import com.StevanZecic.NotBooking.dto.SignupRequest;
import com.StevanZecic.NotBooking.dto.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signupRequest);

}
