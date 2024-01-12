package com.lygin.beekeepersshopapi.services.impl;

import com.lygin.beekeepersshopapi.dto.ProductDto;
import com.lygin.beekeepersshopapi.entity.Category;
import com.lygin.beekeepersshopapi.entity.Product;
import com.lygin.beekeepersshopapi.repositories.CategoryRepository;
import com.lygin.beekeepersshopapi.repositories.ProductRepository;
import com.lygin.beekeepersshopapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto create(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();

        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
