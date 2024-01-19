package com.lygin.beekeepersshopapi.dto;

import lombok.Data;

@Data
public class CartItemsDto {

    private Long id;
    private Long price;
    private Long quality;
    private Long productId;
    private Long orderId;
    private String productName;
    private byte[] returnedImg;
    private Long userId;
}
