package com.lygin.beekeepersshopapi.controllers;

import com.lygin.beekeepersshopapi.entity.User;
import com.lygin.beekeepersshopapi.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi user");
    }
}
