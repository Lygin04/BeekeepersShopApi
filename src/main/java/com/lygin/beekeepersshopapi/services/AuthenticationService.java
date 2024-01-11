package com.lygin.beekeepersshopapi.services;

import com.lygin.beekeepersshopapi.dto.JwtAuthenticationResponse;
import com.lygin.beekeepersshopapi.dto.RefreshTokenRequest;
import com.lygin.beekeepersshopapi.dto.SignInRequest;
import com.lygin.beekeepersshopapi.dto.SignUpRequest;
import com.lygin.beekeepersshopapi.entity.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
