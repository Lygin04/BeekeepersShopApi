package com.lygin.beekeepersshopapi.controllers;

import com.lygin.beekeepersshopapi.dto.ProductDto;
import com.lygin.beekeepersshopapi.entity.Product;
import com.lygin.beekeepersshopapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ProductDto> create(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto product = productService.create(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAll(){
        List<ProductDto> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllByName(@PathVariable String name){
        List<ProductDto> products = productService.getAllByName(name);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean delete = productService.deleteProduct(id);
        if(delete){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
