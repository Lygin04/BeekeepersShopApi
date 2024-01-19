package com.lygin.beekeepersshopapi.services.impl;

import com.lygin.beekeepersshopapi.entity.Coupon;
import com.lygin.beekeepersshopapi.exceptions.ValidationException;
import com.lygin.beekeepersshopapi.repositories.CouponRepository;
import com.lygin.beekeepersshopapi.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    public Coupon create(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())) {
            throw new ValidationException("Coupon code already exists.");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAll(){
        return couponRepository.findAll();
    }
}
