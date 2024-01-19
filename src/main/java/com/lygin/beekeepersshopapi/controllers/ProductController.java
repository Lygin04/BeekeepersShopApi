package com.lygin.beekeepersshopapi.controllers;

import com.lygin.beekeepersshopapi.dto.ProductDto;
import com.lygin.beekeepersshopapi.entity.Product;
import com.lygin.beekeepersshopapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        ProductDto product = productService.create(productDto);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAll(){
        List<ProductDto> products = productService.getAll();
        return ResponseEntity.ok(products);
    }
}
