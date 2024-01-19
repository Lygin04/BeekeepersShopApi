package com.lygin.beekeepersshopapi.controllers;

import com.lygin.beekeepersshopapi.entity.Coupon;
import com.lygin.beekeepersshopapi.exceptions.ValidationException;
import com.lygin.beekeepersshopapi.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Coupon coupon){
        try {
            Coupon createdCoupon = couponService.create(coupon);
            return ResponseEntity.ok(createdCoupon);
        }
        catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAll(){
        return ResponseEntity.ok(couponService.getAll());
    }
}
