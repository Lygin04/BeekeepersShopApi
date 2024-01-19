package com.lygin.beekeepersshopapi.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private Long userId;
    private String userRole;
    private String token;
    private String refreshToken;
}
