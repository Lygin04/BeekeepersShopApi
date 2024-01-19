package com.lygin.beekeepersshopapi.services;

import com.lygin.beekeepersshopapi.dto.AddProductCartDto;
import com.lygin.beekeepersshopapi.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProduct(AddProductCartDto addProductCartDto);
    OrderDto getCartByUserId(Long id);
}
