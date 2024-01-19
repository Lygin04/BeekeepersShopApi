package com.lygin.beekeepersshopapi.services.impl;

import com.lygin.beekeepersshopapi.dto.AddProductCartDto;
import com.lygin.beekeepersshopapi.dto.CartItemsDto;
import com.lygin.beekeepersshopapi.dto.OrderDto;
import com.lygin.beekeepersshopapi.entity.*;
import com.lygin.beekeepersshopapi.repositories.CartItemsRepository;
import com.lygin.beekeepersshopapi.repositories.OrderRepository;
import com.lygin.beekeepersshopapi.repositories.ProductRepository;
import com.lygin.beekeepersshopapi.repositories.UserRepository;
import com.lygin.beekeepersshopapi.services.CartService;
import com.lygin.beekeepersshopapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final OrderRepository orderRepository;
    private final CartItemsRepository cartItemsRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<?> addProduct(AddProductCartDto addProductCartDto){
        Order order = orderRepository.findByUserIdAndOrderStatus(addProductCartDto.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId
                (addProductCartDto.getProductId(), order.getId(), addProductCartDto.getUserId());

        if(optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        else {
            Optional<Product> optionalProduct = productRepository.findById(addProductCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductCartDto.getUserId());

            if(optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cartItems = new CartItems();
                cartItems.setProduct(optionalProduct.get());
                cartItems.setPrice(optionalProduct.get().getPrice());
                cartItems.setQuality(1L);
                cartItems.setUser(optionalUser.get());
                cartItems.setOrder(order);

                CartItems updateCart = cartItemsRepository.save(cartItems);

                order.setTotalAmount(order.getTotalAmount() + cartItems.getPrice());
                order.setAmount(order.getAmount() + cartItems.getPrice());
                order.getCartItems().add(cartItems);

                orderRepository.save(order);
                return ResponseEntity.status(HttpStatus.CREATED).body(cartItems);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    public OrderDto getCartByUserId(Long id){
        Order order = orderRepository.findByUserIdAndOrderStatus(id, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtos = order.getCartItems().stream()
                .map(CartItems::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(order.getAmount());
        orderDto.setId(order.getId());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setDiscount(order.getDiscount());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setCartItems(cartItemsDtos);

        return orderDto;
    }
}
