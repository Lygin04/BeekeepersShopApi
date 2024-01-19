package com.lygin.beekeepersshopapi.services;

import com.lygin.beekeepersshopapi.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto) throws IOException;
    List<ProductDto> getAll();
}
