package com.lygin.beekeepersshopapi.services.impl;

import com.lygin.beekeepersshopapi.dto.JwtAuthenticationResponse;
import com.lygin.beekeepersshopapi.dto.RefreshTokenRequest;
import com.lygin.beekeepersshopapi.dto.SignInRequest;
import com.lygin.beekeepersshopapi.dto.SignUpRequest;
import com.lygin.beekeepersshopapi.entity.Order;
import com.lygin.beekeepersshopapi.entity.OrderStatus;
import com.lygin.beekeepersshopapi.entity.Role;
import com.lygin.beekeepersshopapi.entity.User;
import com.lygin.beekeepersshopapi.repositories.OrderRepository;
import com.lygin.beekeepersshopapi.repositories.UserRepository;
import com.lygin.beekeepersshopapi.services.AuthenticationService;
import com.lygin.beekeepersshopapi.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final OrderRepository orderRepository;

    public User signup(SignUpRequest signUpRequest){
        if(userRepository.findByEmail(signUpRequest.getEmail()).isEmpty())
            throw new UsernameNotFoundException("user with such an email already exists");
        User user = new User();
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        if(signUpRequest.getRole().equals("BUYER"))
            user.setRole(Role.BUYER);
        else if(signUpRequest.getRole().equals("SELLER"))
            user.setRole(Role.SELLER);

        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepository.save(order);

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setUserRole(user.getRole().name());

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }
}
