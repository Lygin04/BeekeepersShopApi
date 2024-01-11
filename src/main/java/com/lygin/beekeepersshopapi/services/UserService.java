package com.lygin.beekeepersshopapi.services;

import com.lygin.beekeepersshopapi.dto.SignUpRequest;
import com.lygin.beekeepersshopapi.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
}
