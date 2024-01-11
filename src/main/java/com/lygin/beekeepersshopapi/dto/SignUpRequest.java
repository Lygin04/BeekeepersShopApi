package com.lygin.beekeepersshopapi.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String role;
}
