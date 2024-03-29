package com.lygin.beekeepersshopapi.controllers;

import com.lygin.beekeepersshopapi.dto.AddProductCartDto;
import com.lygin.beekeepersshopapi.dto.OrderDto;
import com.lygin.beekeepersshopapi.entity.Order;
import com.lygin.beekeepersshopapi.exceptions.ValidationException;
import com.lygin.beekeepersshopapi.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddProductCartDto addProductCartDto){
        return cartService.addProduct(addProductCartDto);
    }

    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<?> addProduct(@PathVariable Long id){
        OrderDto orderDto = cartService.getCartByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code){
        try {
            OrderDto orderDto = cartService.applyCoupon(userId, code);
            return ResponseEntity.ok(orderDto);
        }
        catch (ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
